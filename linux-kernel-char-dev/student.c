
#include <linux/printk.h>

#include <linux/module.h>
#include <linux/moduleparam.h>
#include <linux/device.h>
#include <linux/fs.h>
#include <linux/uaccess.h>
#include <linux/cdev.h>

#define	STUDENT_CHRDEV "marvin-the-robot"

static int student_chrdev_major = 0;
static struct device *my_dev = NULL;

typedef struct student_internal {

} st_int_t;

#if 0
static st_int_t* get_priv_data(struct file *file) {
	return (st_int_t*)file->private_data;
}

static void set_priv_data(struct file *file, st_int_t *data) {
	file->private_data = data;
}
#endif

//Global Values
static int global_val = CONFIG_DUMMY_VAL;
static int debug_mode = CONFIG_DEBUG_VAL;

// For changing the value at insmod time
/*
#ifdef CONFIG_DEBUG
static bool debug = true;
#else
static bool debug = false;
#endif
module_param(debug, bool, 0444); // 0444 = read-only for user, read-write for root
MODULE_PARM_DESC(debug, "Enable debug mode (from Kconfig DEBUG)"); 

*/

#undef pr_fmt 
#define pr_fmt(fmt) KBUILD_MODNAME ": %s(): " fmt, __func__ 
// overwrting general prefix all prints with module name

#define DPRINT(fmt, ...)                 \
    do {                                 \
        if (debug_mode)                       \
            pr_info(fmt, ##__VA_ARGS__); \
    } while (0)
// Print function

static ssize_t student_write(struct file *file, const char __user *buf, size_t count, loff_t *ppos) {
	char kbuf[16];
    int val;
	
    DPRINT("Fucntion student write called");

    if (count > sizeof(kbuf) - 1)
        return -EINVAL;

    if (copy_from_user(kbuf, buf, count))
        return -EFAULT;

    kbuf[count] = '\0';

    if (kstrtoint(kbuf, 10, &val))
        return -EINVAL;

    global_val += val;

    pr_info("Updated global_val: %d\n", global_val);

    return count;
}

static const struct file_operations student_fops = {
  .owner    = THIS_MODULE,
  .write     = student_write,
};
static struct class universe_class = {
	.name		= STUDENT_CHRDEV,
};

static int __init student_chrdevinit(void) {
	int err;

	DPRINT("Starting student driver in DEBUG mode\n");
	DPRINT("Preconfigured DUMMY_VAL = %d\n", global_val);

	student_chrdev_major = err = register_chrdev(0, STUDENT_CHRDEV, &student_fops);
	if (err < 0) {
		pr_err("Cannot register chrdev: %d\n", err);
		goto out;
	}
	err = class_register(&universe_class);
    if (err) {
		pr_err("Cannot register universe class: %d\n", err);
		goto out_chrdev;
	}
	my_dev = device_create(&universe_class, NULL, MKDEV(student_chrdev_major, 0), NULL, STUDENT_CHRDEV);
	if (IS_ERR(my_dev)) {
		err = PTR_ERR(my_dev);
		pr_err("Cannot create device: %d\n", err);
		goto out_class;
	}
	pr_notice("Loaded module %s\n", KBUILD_MODNAME);
	return 0;

out_class:
	class_unregister(&universe_class);
out_chrdev:
	unregister_chrdev(student_chrdev_major, STUDENT_CHRDEV);
out:
	return err;
}

static void __exit student_chrdevexit(void) {
	device_destroy(&universe_class, MKDEV(student_chrdev_major, 0));
	class_unregister(&universe_class);
	unregister_chrdev(student_chrdev_major, STUDENT_CHRDEV);
	pr_notice("Unloaded module %s\n", KBUILD_MODNAME);
}

module_init(student_chrdevinit);
module_exit(student_chrdevexit);
MODULE_LICENSE("GPL");

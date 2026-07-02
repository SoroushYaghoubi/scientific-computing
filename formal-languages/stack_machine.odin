#+feature dynamic-literals

package main

import "core:fmt"
import "core:os"
import "core:slice"
import "core:strconv"
import "core:strings"

ANY :: union {
	string,
	bool,
	int,
}

// stack: [1 2 ... b a]
//                        ^
//                len(stack)-1
interpret :: proc(tokens: []string) -> [dynamic]ANY {
	stack: [dynamic]ANY

	for i := 0; i < len(tokens); i += 1 {
        fmt.println(i, stack)

		token := tokens[i]
		if val, ok := strconv.parse_int(token); ok {
			append(&stack, ANY(cast(int)val))
		} else if val, ok := strconv.parse_bool(token); ok {
			append(&stack, ANY(cast(bool)val))
		} else {
			// standard operators
			switch token {
			case "+":
				a := pop(&stack).(int)
				b := pop(&stack).(int)
				append(&stack, a + b)
			case "*":
				a := pop(&stack).(int)
				b := pop(&stack).(int)
				append(&stack, a * b)
			case "-":
				a := pop(&stack).(int)
				b := pop(&stack).(int)
				append(&stack, a - b)
			case "++":
				a := pop(&stack).(int)
				append(&stack, a + 1)
			case "--":
				a := pop(&stack).(int)
				append(&stack, a - 1)
			case ">":
				a := pop(&stack).(int)
				b := pop(&stack).(int)
				append(&stack, b > a)
			case "<":
				a := pop(&stack).(int)
				b := pop(&stack).(int)
				append(&stack, b < a)
            case "==":
				a := pop(&stack).(int)
				b := pop(&stack).(int)
				append(&stack, a == b)
			case "%":
				a := pop(&stack).(int)
				b := pop(&stack).(int)
				append(&stack, a % b)
			// logic commands
			case "or":
				a := pop(&stack).(bool)
				b := pop(&stack).(bool)
				append(&stack, a || b)
			case "and":
				a := pop(&stack).(bool)
				b := pop(&stack).(bool)
				append(&stack, a && b)
			case "not":
				a := pop(&stack).(bool)
				append(&stack, !a)
			// stack commands
			case "swap":
				a := pop(&stack)
				b := pop(&stack)
				append(&stack, a)
				append(&stack, b)
			case "dup":
				a := pop(&stack)
				append(&stack, a)
				append(&stack, a)
			// flow control
            case "runbackif":
                cond := pop(&stack).(bool)
                if !cond { continue }

                checkpoint: int
                for checkpoint = i-1; checkpoint >= 0; checkpoint -= 1 {
                    if tokens[checkpoint] == "checkpoint" {
                        break
                    }
                }

                i = checkpoint
                continue
            }
		}
	}

	return stack
}

main :: proc() {
	buf: [64]byte
	for {
		fmt.print("> ")
		n, _ := os.read(os.stdin, buf[:])
		prompt := string(buf[:n])
		tokens := strings.fields(prompt)

		stack := interpret(tokens)
		defer delete(stack)

		fmt.println(stack)
		fmt.println()
	}
}

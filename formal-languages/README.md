# lang project
> Trying to learn languages more deeply.

___
## Beta Reducer for Lambda Expressions
> This is a beta reducer for λ-expressions. Right now it only works if you put a million parantheses. Enjoy :>

### Supported characters
- 'a'...'z'
  - 'q' is taken for quitting the program
- '.'
- '(' & ')'

### Supported expressions (also spacing matters)
- `x`
- `(\x.M)`
- `(MM)`

### Comptime formatting
Use `MOD_QUIT`, `MOD_INTERNALS` and `MOD_UPPER` to tweak output behaviour.

___
## Stack Machine
- Interpretting things onto the stack.
- A cool feature right now is conditional jumps. Use `checkpoint`, then `jbif` to consume a boolean from the top of the stack.

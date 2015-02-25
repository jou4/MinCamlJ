let a = if true then 10 else 20 in
let b = if false then 10 else 20 in
let c = if a = 10 then true else false in
let d = if b = 10 then true else false in
let _ = print_bool c in
print_bool d

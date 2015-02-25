let a = 1.2 in
let b = 2.3 in
let c = if a = 1.2 then 1 else 0 in
let d = if a = 1.21 then 1 else 0 in
let e = if a > 1.19 then 1 else 0 in
let f = if a > 1.2 then 1 else 0 in
let g = if a >= 1.2 then 1 else 0 in
let h = if a >= 1.21 then 1 else 0 in
let i = if a < 1.21 then 1 else 0 in
let j = if a < 1.2 then 1 else 0 in
let k = if a <= 1.2 then 1 else 0 in
let l = if a <= 1.19 then 1 else 0 in
let m = if a <> 1.21 then 1 else 0 in
let n = if a <> 1.2 then 1 else 0 in
let _ = print_int c in
let _ = print_int d in
let _ = print_int e in
let _ = print_int f in
let _ = print_int g in
let _ = print_int h in
let _ = print_int i in
let _ = print_int j in
let _ = print_int k in
let _ = print_int l in
let _ = print_int m in
print_int n

let a = (true, 1, 2.3) in
let b = (false, 2, 4.5) in
let (c, d, e) = a in
let (f, g, h) = b in
let i = (if c then 1 else 0, if d < g then d + g else d - g, if e < h then h else e) in
print_tuple i

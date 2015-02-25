let a = Array.make 3 0 in
let _ = a.(0) <- 3 in
let _ = a.(1) <- 2 in
let _ = a.(2) <- 1 in
let b = (a.(2), a.(1), a.(0)) in
let _ = print_array a in
print_tuple b

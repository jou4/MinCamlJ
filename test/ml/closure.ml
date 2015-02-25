let rec make_adder x = 
  let rec f y =
      let rec g z = x + y + z in g in f in
let a = make_adder 10 in
let b = a 20 in
let _ = print_int (b 30) in
let rec make_adder_float x =
    let rec f y = 
      let rec g z = x +. y +. z in g in f in
let a = make_adder_float 1.0 in
let b = a 2.0 in
let _ = print_float (b 3.0) in
let rec make_tuple x = 
  let rec f y =
    let rec g z = (x, y, z) in g in f in
let a = make_tuple 1 in
let b = a 2 in
print_tuple (b 2.0)

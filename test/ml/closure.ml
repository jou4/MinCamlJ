let rec make_adder x = 
  let rec f y =
      let rec g z = x + y + z in g in f in
let a = make_adder 10 in
let b = a 20 in
let _ = print_int (b 30) in
let rec make_adder2 x = 
  let rec f y z = x + y + z in f in
let a = make_adder 10 in
let b = a 20 in
let _ = print_int (b 30) in
let rec make_adder_float x =
  let rec f y = 
    let rec g z = x +. y +. z in g in f in
let a = make_adder_float 1.0 in
let b = a 2.0 in
let _ = print_float (b 3.0) in
let rec make_adder_float2 x =
  let rec f y z = x +. y +. z in f in
let a = make_adder_float 1.0 in
let b = a 2.0 in
let _ = print_float (b 3.0) in
let rec make_tuple x = 
  let rec f y =
    let rec g z = (x, y, z) in g in f in
let a = make_tuple 1 in
let b = a 2 in
let _ = print_tuple (b 2.0) in
let rec counter x =
  let ref = Array.make 1 0 in
  let rec f x =
    let n = ref.(0) + 1 in
    let _ = ref.(0) <- n in n in f in
let f = counter 0 in
let a = f 0 in
let b = f 0 in
let c = f 0 in
print_int (a + b + c)

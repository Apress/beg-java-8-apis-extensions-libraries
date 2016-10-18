// stream.js
var list;
if (arguments.length == 0) {
    list = [1, 2, 3, 4, 5];
}
else {
  list = arguments;   
}

print("List of numbers: " + list);

var sumOfSquaredOdds = list.filter(function(n) {return n % 2 == 1;})
                           .map(function(n) {return n * n;})
                           .reduce(function(sum, n) {return sum + n;}, 0);

print("Sum of the squares of odd numbers: " + sumOfSquaredOdds);

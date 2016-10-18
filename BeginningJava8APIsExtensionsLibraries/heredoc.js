// heredoc.js
var str = <<EOF
This is a multi-line string.
Number of arguments passed to this
script is ${$ARG.length}
Arguments are ${$ARG}

Bye Heredoc!
EOF

print(str);

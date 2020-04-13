# IO - java console made easy

Are you tired of buffers, scanners, casting and friends? You just need an int from the console?
Then this is for you!

It's dead simple:
<code>int i = Console.getInstance().readInt();</code>

It takes care of prompting the user, retrying, parsing and all that for you.
<code>Console</code> implements TypedIO and knows how to read LocalDate, LocalDateTime too.
Actually it knows how to read all the primitive types and some other useful stuff.

Java versions supported: 11+

Tip: you can implement your own TypedIO to read from and write to anything you like, not just System.in/out

## Menu functionality
You can do this:

<code>char c = Console.getInstance().choice(MY_STRING_MENU);</code>

and you don't have to bother with terminal UX in your own code anymore.

## Author
Dan Nicolici

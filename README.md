# chalk
> Terminal string styling done right (in java)

Chalk is focusing on cross-platform standard output coloring in java.

The coloring should work:
* In Ux terminals supporting ANSI coloring
* In IDEs supporting ANSI coloring (such as IntelliJ Idea)
* In CI tools supporting ANSI coloring (such as Jenkins CI with AnsiColor plugin)
* In Windows command line (yay!)
    * Utilizing the [Jansi library](https://github.com/fusesource/jansi)

## Why
I want java tools to have the same possibilities as javascript.
npm and related tools nicely color output even on Windows;
Java does not have this possibility.
After looking at [Chalk for javascript](https://github.com/chalk/chalk), I have decided
to implement a similar solution in Java.

## Install
As soon as first release is available, we shall have it on Maven
 central.

     <dependency>
       <groupId>com.github.tomas-langer</groupId>
       <artifactId>chalk</artifactId>
       <version>1.0.1</version>
     </dependency>

# Usage
Printing a highlighted text - underlined red:

```java
System.out.println("This message is " + Chalk.on("IMPORTANT").red().underline());
```

will print:

![screenshot](https://cloud.githubusercontent.com/assets/13766491/11868168/493af7ec-a4b6-11e5-985d-cbb3ab9cb8ed.png)

There are some system properties to control behavior:
* jansi.strip - if set to "true", colors will not be sent to output (on any environment)
* jansi.passthrough - if set to "true", ANSI escapes will be sent to output (on any environment, including Windows)
* idea.launcher.bin.path - if set, I know I am running from IntelliJ Idea, will expect output to be ANSI compliant

The following CI tools are currently recognized:
* Hudson - if in Hudson, ANSI escapes are passed through (requires AnsiColor plugin)
* Jenkins - if in Jenkins, ANSI escapes are passed through (requires AnsiColor plugin)

## Styles
Only those supported at least on two of: Windows, Linux, Idea and Jenkins plugin are included.

### Modifiers

- `bold`
- `underline` *(will use a different background on Windows, underlining not supported)*
- `inverse`

### Colors

- `black`
- `red`
- `green`
- `yellow`
- `blue` *(this color is illegible on Windows with black background)*
- `magenta`
- `cyan`
- `white`
- `gray` *(just black on Windows - requires light background)*

### Background colors

- `bgBlack`
- `bgRed`
- `bgGreen`
- `bgYellow`
- `bgBlue`
- `bgMagenta`
- `bgCyan`
- `bgWhite`



## License

[Apache License](http://www.apache.org/licenses/)
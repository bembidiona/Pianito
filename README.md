# Pianito
A handy GUI object for playing SC Synths with the computer keyboard as in [FL Studio](https://raw.githubusercontent.com/bembidiona/Pianito/master/HelpSource/Images/layout.png), with a couple of extra features.

<p align="center">
  <img width="458" height="173" src="https://raw.githubusercontent.com/bembidiona/Pianito/master/HelpSource/Images/pianito.png">
</p>

## How to Install

Open a new document on your SuperCollider IDE and type:

```
Quarks.install("https://github.com/bembidiona/Pianito");
```

After a few seconds, you should see the following printed in the Post window:

```
Installing Pianito
Pianito installed
-> Quark: Pianito
```

Finally recompile your class library. Go to `Language` menu, `Recompile class
library`, or hit <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>L</kbd>.

## How to Run
Assuming you had already added a SynthDef named `\yoursynth`, just evaluate:
```
Pianito(\yoursynth);
```

## How to Play
| key      | function       |
| ---      | ---       |
| [ZSXDC...](https://raw.githubusercontent.com/bembidiona/Pianito/master/HelpSource/Images/layout.png)| Play a note |
| Space | Sustain pedal |
| Tab | Toggle holding notes |
| Left | Transpose down a semitone |
| Right | Transpose up a semitone |
| Down | Transpose down an octave |
| Up | Transpose up an octave |
| Home | Reset transposition |

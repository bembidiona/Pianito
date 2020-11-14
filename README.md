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
#### with custom Synths
Assuming you had already added a SynthDef named `\yoursynth`, just evaluate:
```
Pianito(\yoursynth);
```

#### with SuperDirt Synths
First start SuperDirt:
```
SuperDirt.start;
```
Now for using a SuperDirt's instrument as `supersaw`, evaluate:
```
Pianito(~dirt, \supersaw);
```
And for using a sample, the second argument is the folder or `s` in tidal, and the third is the sample number or `n` in tidal:
```
Pianito(~dirt, \bass, 2);
```

Also the [help file](https://github.com/bembidiona/Pianito/blob/master/HelpSource/Classes/Pianito.schelp) has a couple of examples.

## How to Play
<p align="center">
  <img width="700" src="https://raw.githubusercontent.com/bembidiona/Pianito/master/HelpSource/Images/layout.png">
</p>

| Special Keys      | Function       |
| ---      | ---       |
| Space | Sustain pedal |
| Tab | Toggle holding notes |
| Left | Transpose down a semitone |
| Right | Transpose up a semitone |
| Down | Transpose down an octave |
| Up | Transpose up an octave |
| Home | Reset transposition |

| Sliders | for simpleSynthdefs | for SuperDirt's Instrument | for SuperDirt's sample |
| --- | --- | --- | --- |
| 1 | \amp | gain | gain |
| 2 | \mod | sustain | pan |

## Notes
- You can create a any number of Pianitos, each one playing a diferent synth. But only the focused one will play.
- If you are typing on the SC IDE, Pianito will not play.
- SynthDef are expected to have a `freq` for the note to work, an `amp` for amplitude to work, and `gate` for the hold mode or sustain to work.
- SuperDirt's instruments don't have a sustained envelope so Sustain and Hold keys don't work with them. You need to clone the SynthDef and add a proper gate if you want those features.

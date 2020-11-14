# Pianito
A handy GUI object for playing Synths with the computer keyboard as in [FL Studio](https://raw.githubusercontent.com/bembidiona/Pianito/master/HelpSource/Images/layout.png), with a couple of extra features.

<p align="center">
  <img width="458" height="173" src="https://raw.githubusercontent.com/bembidiona/Pianito/master/HelpSource/Images/pianito.png">
</p>

## How to Install
Just evaluate this in the SuperCollider IDE:\
`Quarks.install("https://github.com/bembidiona/Pianito");`

## How to Run
Assuming you had already added a SynthDef named `\yoursynth`, just evaluate:\
`Pianito(\yoursynth);`

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

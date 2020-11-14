# Pianito
A handy GUI object for playing Synths with the computer keyboard as in FL Studio and other DAWs

## How to Install
Just evaluate this in the SuperCollider IDE:\
`Quarks.install("https://github.com/bembidiona/Pianito");`

## How to Run
assuming you had already added a SynthDef named `\yoursynth`, just evaluate:\
`Pianito(\yoursynth);`

## How to Play
| key      | function       |
| ---      | ---       |
| Z S X D C ... Q 2 W 3 E ... | Play a note |
| Space | Sustain pedal |
| Tab | Toggle holding notes |
| Left | Transpose down a semitone |
| Right | Transpose up a semitone |
| Down | Transpose down an octave |
| Up | Transpose up an octave |
| Home | Reset transposition |



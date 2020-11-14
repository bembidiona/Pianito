# Pianito
A handy GUI object for playing Synths with the computer keyboard as in [FL Studio](https://i.pinimg.com/originals/46/02/86/4602862df84be4e8c164227c3e144207.png), with a couple of extra features.

## How to Install
Just evaluate this in the SuperCollider IDE:\
`Quarks.install("https://github.com/bembidiona/Pianito");`

## How to Run
Assuming you had already added a SynthDef named `\yoursynth`, just evaluate:\
`Pianito(\yoursynth);`

## How to Play
| key      | function       |
| ---      | ---       |
| [ZSXDC...](https://i.pinimg.com/originals/46/02/86/4602862df84be4e8c164227c3e144207.png)| Play a note |
| Space | Sustain pedal |
| Tab | Toggle holding notes |
| Left | Transpose down a semitone |
| Right | Transpose up a semitone |
| Down | Transpose down an octave |
| Up | Transpose up an octave |
| Home | Reset transposition |

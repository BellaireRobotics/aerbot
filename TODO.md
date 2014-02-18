TODO
====

1. Pneumatics for intake up/down - use spike relay output
2. autoshifting based on speed - trapezoidal integral? (sure). B button should be a toggle between autoshift and low gear. shift to high gear when speed is 70-80% of max speed at low gear. meaning, around 5.5 feet/sec
3. mess around with gyro straight driving- as long as we are holding forward/backward, enable straight driving. when we are turning, disable gyro straightening UNTIL we leave the turn and go straight again. then, set new heading to whatever we turned to.
4. hotkeys- use gyro to turn 90 degrees quickly with D-pad left/right
5. autonomous- what do? put in some code to move our bot forward for the 5pts mobility bonus
6. come up with some way to reset gyro/accelerometer- integrals will accumulate error due to estimation and will need to be zeroed every now and then- will error be a problem in a 2.5 minute time span? (maybe, but probably not if we use the cpu timer (does that have lots of error?).) CPU timer has no error but since we are reimann sum there is always error
7. pneumatic pump- disable pump whenever joysticks are moving above some low speed like 0.07/1, this is to keep battery voltage high and avoid disconnections
8. figure out control scheme for intake up/down/in/out/shooter. maybe dual joysticks, partner drive.

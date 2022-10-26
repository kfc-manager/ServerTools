# ServerTools

## Commands
**/gamemode**
- accepts two arguments, but requires at least one
- first argument: an integer in the range of 0-3 (determining which gamemode)
- second argument: the name of a player (needs to be online)
- integer 0: survival
- integer 1: creative
- integer 2: adventure
- integer 3: spectator
- sets the gamemode of the command sender (except sender is the console) if only one argument is passed

**/kill**
- accepts one argument, but does not require it
- if no argument is passed: the sender gets killed (unless the sender is the console)
- if the name of an online player is passed: that player gets killed

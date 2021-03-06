![build-status](https://travis-ci.org/Jasper-ketelaar/Asteroids-group-7.svg?branch=master)
# Asteroids Group 7

For TI2206 Software Engineering Methods.

## How to install
To get this project to work on your local computer you will first have to clone the project if you haven't already, then a few steps have to be taken:

1. Import your project to eclipse if you haven't already (File -> Import -> Existing Maven Projects -> Select pom.xml)
2. Right click Asteroids-group-7 and click on 'properties'
3. Navigate to 'Java Build Path' and click on the tab 'Libraries'
4. Select 'Maven Dependencies' and expand
5. Click on 'Native library location' and press 'Edit...'
6. Press 'Workspace...' and select the folder 'lwjgl/native/windows'
7. Navigate back to the project by pressing 'Ok' (3 times)
8. The project should be executable now!

## Ingame image
![ingame-image](https://api.monosnap.com/rpc/file/download?id=N2MQZAfANGGQHILv9oOZAyghG9BQse "Ingame image")

### Multiplayer
Run the program with -mp program argument to get a local multiplayer instance. Menu options will be provided later on.

### Powerups
There are three powerups in the game that spawn in semi-randomly:

1. BULLET - This powerup is red of color and will increase your bullet speed for 10 seconds.

2. POINTS - This powerup is green of color and will double your points gained for 8 seconds.

3. INVINCIBLE - This powerup is yellow of color and will give you invincibility for 5 seconds.

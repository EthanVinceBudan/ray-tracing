# Ray Tracing

**Ray Tracing** is a tool for rendering user-defined scenes using a path tracing algorithm. ***NOTE***:
currently the automatic build tools for Ray Tracing are only supported on Windows.

# Setup

1. Clone this repository to your local machine.
2. (Windows only) Run the file `MAKE.bat`. This will do the following:
	- Compile the Java source code into class files.
	- Package the compiled files into a JAR.
	- Generate documentation for all class interfaces.

You are now ready to run the program.

# Usage

Be sure to complete the steps in the `Setup` section before moving on.

To run **Ray Tracing**, simply use the following command in the main directory of the repo:

> java -jar renderer.jar

This will use the default scene setup included in the repository called `default.scene`. You can also
specify which scene file you would like to use by passing an argument:

> java -jar renderer.jar SCENE_FILE

Where SCENE_FILE is the relative path to your scene file. More on the syntax of scene files can be found
in the `Scene Files` section.

Finally, using the flag `-doc` will simply open the documentation for **Ray Tracing**, assuming that it
has been generated (this happens automatically if you compiled using MAKE.bat).

# Scene Files

Scene files are a quick way to define an entire image to be rendered by the program. This includes all
the objects that will be present, the materials that those objects will use, camera location and field
of view, in addition to output formatting such as image size and name.

The safest way to create a scene file is to copy the default scene and make modifications as necessary.
This will ensure that the file has a proper structure that can be interpreted by the program. Within this
file are some brief explanations on how to create materials and objects that will be present in the scene.
However, for a slightly more detailed explanation of how to create objects and materials, continue below.

## Material Definitions

For a material, the syntax is `name: r,g,b isEmitter isSink`, where name is the unique
identifier of the material, r g and b are the color components of the material, and isEmitter and isSink
tell the renderer whether the material will emit light, or absorb all light respectively.

## Object Definitions

For an object, the syntax is somewhat dependent on the type of shape to be created. however, the general
format follows the syntax `type: location [parameters] material`. The shape type must be one of either
`sphere` or `plane`, otherwise the interpreter will not know what object to create. Location is always a
set of comma separated values for the x,y,z position of the object. The material must be one of the
material names defined earlier in the materials section of the scene file.

Object parameters varies based on the shape to be created: for a sphere, this parameter is simply the 
radius. However, for a plane the parameter is a vector specified as x,y,z that represents the normal
vector of that surface.
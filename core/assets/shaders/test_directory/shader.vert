attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;
uniform float u_time;

varying vec4 v_color;
varying vec4 worldCoord;
varying vec2 v_texCoords;

void main(void)
{

    float angle =  u_time * 0.05f;
    mat4  rotation = mat4(
    vec4( cos(angle), -sin(angle), 0.0,  0.0 ),
    vec4( sin(angle), cos(angle),  0.0,  0.0 ),
    vec4( 0.0,        0.0,         1.0,  0.0 ),
    vec4( 0.0,        0.0,         0.0,  1.0 ) );

    v_color = a_color;
    v_texCoords = a_texCoord0;
    gl_Position = u_projTrans * a_position * rotation;
    worldCoord = gl_ModelViewMatrix * gl_Vertex;
}
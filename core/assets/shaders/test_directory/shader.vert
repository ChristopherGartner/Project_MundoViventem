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

    float angle =  u_time * 0.5f;
    float a = mod(angle, 360);
    //mat4  rotation = mat4(
    //vec4( cos(angle), -sin(angle), 0.0,  0.0 ),
    //vec4( sin(angle), cos(angle),  0.0,  0.0 ),
    //vec4( 0.0,        0.0,         1.0,  0.0 ),
    //vec4( 0.0,        0.0,         0.0,  1.0 ) );

    vec2 rotatedPos = vec2(
        a_position.x * cos(a*3.1415/180) + a_position.y * sin(a*3.1415/180),
        a_position.y * cos(a*3.1415/180) - a_position.x * sin(a*3.1415/180)
    );
    v_color = a_color;
    v_texCoords = a_texCoord0;
    gl_Position = u_projTrans * vec4(rotatedPos, 0, 1);
    worldCoord = gl_ModelViewMatrix * gl_Vertex;
}
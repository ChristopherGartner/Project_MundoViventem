#version 120

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

uniform vec2 u_resolution;
uniform float u_time;
uniform vec2 u_mouseLocation;

void main()
{
  gl_FragColor = texture2D(u_texture, v_texCoords);
}

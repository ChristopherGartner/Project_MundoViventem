varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform vec2 u_resolution;

void main()
{
  gl_FragColor = vec4(0.7f, 0.2f, 0.8f, 1.0f) * texture2D(u_texture, v_texCoords);
}
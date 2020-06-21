varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform vec2 u_resolution; //x width y height
uniform float u_time;
uniform vec2 u_mouseLocation; //x width y height
uniform vec2 u_textureRes;

void main()
{
  float time = u_time;
  vec3 rainbow = vec3((sin(time) + 1.0f )/ 2.0f,
  (sin(time-3.1415f*2.0f/3.0f)+1.0f)/2.0f,
  (sin(time+3.1415f*2.0f/3.0f)+1.0f)/2.0f);


  vec2 mouseNormalized = vec2(u_resolution.x / u_mouseLocation.x, u_resolution.y / u_mouseLocation.y);

  float dst = distance(v_texCoords, mouseNormalized);
  vec4 newColor = vec4(1.0f);
  if(dst < 0.5f){
    newColor = vec4(rainbow, 1.0f);
  }

  gl_FragColor = newColor * texture2D(u_texture, v_texCoords);
}
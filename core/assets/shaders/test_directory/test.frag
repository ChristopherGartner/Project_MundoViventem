varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform vec2 u_resolution; //x width y height
uniform float u_time;
uniform vec2 u_mouseLocation; //x width y height
uniform vec2 u_textureRes;

//v_texCoords
//0,0 1,0
//0,1 1,1

//u_resolution / u_mouselocation
//0,0 1,0
//0,1 1,1

void main()
{
  float time = u_time;
  vec3 rainbow = vec3((sin(time) + 1.0f )/ 2.0f,
  (sin(time-3.1415f*2.0f/3.0f)+1.0f)/2.0f,
  (sin(time+3.1415f*2.0f/3.0f)+1.0f)/2.0f);


  vec2 mouseNormalized = vec2(u_mouseLocation.x / u_resolution.x , u_mouseLocation.y / u_resolution.y);

  float dst = distance(v_texCoords, vec2(mouseNormalized.x , mouseNormalized.y));
  vec4 newColor = vec4(1.0f);
  if(dst < 0.7f){
    newColor = vec4(rainbow, 1.0f);
  }

  gl_FragColor = newColor * texture2D(u_texture, v_texCoords);
}
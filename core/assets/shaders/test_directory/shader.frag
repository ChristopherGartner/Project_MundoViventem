#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec4 worldCoord;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
//uniform float u_percent;
//uniform vec2 u_resolution;  // Canvas size (width,height)
//uniform vec2 u_mouse;       // mouse position in screen pixels
uniform float u_time;       // Time in seconds since load


void main(void)
{
    vec4 localColor = v_color * texture2D(u_texture, v_texCoords);

    float time = 0.05f * u_time;
    //float mediumOfRgbChannels = (localColor.r + localColor.g + localColor.b) / 3.0;
    /*
    float offsetLight = 0.1;
    float offsetDark = 0.9;

    vec4 finalColor;

    vec2 radius = vec2(0.1, 0.0);
    float distance = abs(length(u_mouse - vec2(gl_FragCoord.x,gl_FragCoord.y)))/8.0;
    float smoothRed = smoothstep(localColor.r-offsetLight,localColor.r-offsetDark, distance);
    float smoothGreen = smoothstep(localColor.g-offsetLight, localColor.g-offsetDark, distance);
    float smoothBlue = smoothstep(localColor.b-offsetLight, localColor.b-offsetDark, distance);

*/
    //if(distance < abs(length(radius))){
        //finalColor = vec4(smoothRed,smoothGreen, smoothBlue, localColor.a);
    //} else {
    //   finalColor = vec4(localColor.r-0.5, localColor.g-0.5, localColor.b-0.5, localColor.a);
    //}
    vec3 rainbow = vec3((sin(time) + 1.0f )/ 2.0f,
    (sin(time-3.1415f*2.0f/3.0f)+1.0f)/2.0f,
    (sin(time+3.1415f*2.0f/3.0f)+1.0f)/2.0f);


    gl_FragColor = localColor * vec4(rainbow, 1.0f);

        /*
    	vec2 position = ( gl_FragCoord.xy / u_resolution.xy ) + u_mouse / 4.0;

    	float color = 0.0;
    	color += sin( position.x * cos( u_time / 15.0 ) * 80.0 ) + cos( position.y * cos( u_time / 15.0 ) * 10.0 );
    	color += sin( position.y * sin( u_time / 10.0 ) * 40.0 ) + cos( position.x * sin( u_time / 25.0 ) * 40.0 );
    	color += sin( position.x * sin( u_time / 5.0 ) * 10.0 ) + sin( position.y * sin( u_time / 35.0 ) * 80.0 );
    	color *= sin( u_time / 10.0 ) * 0.5;

    	gl_FragColor = vec4( vec3( (color+localColor.r)/2.0, (color * 0.5+localColor.g)/2.0, (sin( color + u_time / 3.0 ) * 0.75+localColor.b)/2.0 ), localColor.a );
        */

/*
    vec2 st = gl_FragCoord.xy/u_resolution.xy;
    st.x *= u_resolution.x/u_resolution.y;

    vec3 color = vec3(0.);
    color = vec3((st.x+localColor.r)/2.0,(st.y+localColor.g)/2.0,abs(sin(u_time)));

    gl_FragColor = vec4(abs(sin(u_time)),(st.y+localColor.g)/2.0,(st.x+localColor.b)/2.0,localColor.a);
    */
}

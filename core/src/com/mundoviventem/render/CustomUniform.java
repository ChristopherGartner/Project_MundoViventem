package com.mundoviventem.render;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

/**
 * Data class for easy handling and encapsulating of Uniforms in Shaders. These will be created in Logic classes and
 * passed to shaders. The reference can be edited and so the uniforms in the shader can be updated without requiring
 * the shader itself.
 */

public class CustomUniform {

    /**
     * Datatypes that uniforms can take. These are not all and could be extended if required.
     */

    public enum TYPE {
        FLOAT,
        INT,
        VEC2,
        VEC3,
        VEC4,
        MAT3,
        MAT4
    }

    private TYPE type;
    private String name;
    private float[] values;

    /**
     * A custom uniform consists of:
     * @param uniformIdentifer the name of the uniform as called in the shader (usually starting with u_)
     * @param uniformType the datatype of the uniform values
     * @param uniformValues the uniform values, provided as a float array in to the datatype corresponding size
     */

    public CustomUniform(String uniformIdentifer, TYPE uniformType, float[] uniformValues){
        name = uniformIdentifer;
        type = uniformType;
        values = uniformValues;
    }

    /**
     * Updates the shader with the current values. The shader is already loaded when this method gets called.
     * @param shader
     */

    public void update(ShaderProgram shader){
        try{
            switch(type){
                case FLOAT:
                    shader.setUniformf(name, values[0]);
                    break;
                case INT:
                    shader.setUniformi(name, (int) values[0]);
                    break;
                case VEC2:
                    shader.setUniformf(name, values[0], values[1]);
                    break;
                case VEC3:
                    shader.setUniformf(name, values[0], values[1], values[2]);
                    break;
                case VEC4:
                    shader.setUniformf(name, values[0], values[1], values[2], values[3]);
                    break;
                case MAT3:
                    shader.setUniformMatrix(name, new Matrix3(values));
                    break;
                case MAT4:
                    shader.setUniformMatrix(name, new Matrix4(values));
                    break;
            }
        } catch (IndexOutOfBoundsException e){
            System.err.println("Insufficient values supplied for uniform " + name);
            e.printStackTrace();
        }
    }

    /**
     * Updates the object with new values with will take effect the next time update() gets called
     * @param new_values
     */

    public void updateValue(float[] new_values){
        values = new_values;
    }

    /**
     * Returns the current values as a float array
     * @return
     */

    public float[] getValues(){
        return values;
    }
}

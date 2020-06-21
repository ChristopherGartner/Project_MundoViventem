package com.mundoviventem.render;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;


public class CustomUniform {

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

    public CustomUniform(String uniformIdentifer, TYPE uniformType, float[] uniformValues){
        name = uniformIdentifer;
        type = uniformType;
        values = uniformValues;
    }

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

    public void updateValue(float[] new_values){
        values = new_values;
    }

    public float[] getValues(){
        return values;
    }
}

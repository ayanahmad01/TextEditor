import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring Properties of Text Editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;

    //file Menu Items
    JMenuItem newFile,openFile,saveFile;
    //edit Menu Item
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        //Initialize a Frame
        frame=new JFrame();
        //Initialize a MenuBar
        menuBar=new JMenuBar();
        //Initialize TextArea
        textArea=new JTextArea();

        //Initialize Menus
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //Initialize file Menu Items
        newFile=new JMenuItem("New File");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");
        //Add ActionListeners to file MenuItems
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add MenuItems to file Menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit Menu Items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //Add ActionListeners to edit MenuItems
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Add MenuItems to edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add Menus to MenuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Set MenuBar to Frame
        frame.setJMenuBar(menuBar);

        //Create Content Pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add textArea to Panel
        panel.add(textArea,BorderLayout.CENTER);

        //Create Scroll Pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add ScrollPane to panel
        panel.add(scrollPane);

        //Add panel to frame
        frame.add(panel);

        //Set Dimensions of Frame
        frame.setBounds(50,50,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //Perform Operations on edit MenuItems
        if(actionEvent.getSource()==cut) textArea.cut();
        if(actionEvent.getSource()==copy) textArea.copy();
        if(actionEvent.getSource()==paste) textArea.paste();
        if(actionEvent.getSource()==selectAll) textArea.selectAll();
        if(actionEvent.getSource()==close) System.exit(0);

        //Perform Operations on file MenuItems
        if(actionEvent.getSource()==openFile){
            //Open a FileChooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            //If we have clicked on Open Button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting Selected file
                File file= fileChooser.getSelectedFile();
                //get the Path of Selected File
                String filePath= file.getPath();
                try{
                    //Initialize FileReader
                    FileReader fileReader= new FileReader(filePath);
                    //Initialize BufferedReader
                    BufferedReader bufferedReader= new BufferedReader(fileReader);
                    String Intermediate="", Output="";
                    //Read Content of the File Line by Line
                    while((Intermediate=bufferedReader.readLine())!=null) Output+=Intermediate+"\n";
                    //Set the Output String to textArea
                    textArea.setText(Output);
                } catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //Initialize FileChooser
            JFileChooser fileChooser=new JFileChooser("C:");
            //Get Choose Option from FileChooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //Check if we Clicked on Save Button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a New File with Chosen Directory Path and File Name
                File file= new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize FileWriter
                    FileWriter fileWriter=new FileWriter(file);
                    //Initialize BufferedWriter
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    //Write Contents of the textArea to File
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor= new TextEditor();
        }
    }
    public static void main(String[] args){
        TextEditor texteditor= new TextEditor();
    }
}
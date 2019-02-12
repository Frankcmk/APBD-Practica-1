package com.edu.mx.practica.uno.gui;
import com.edu.mx.practica.uno.convertidor.Convertidor;
import com.edu.mx.practica.uno.persona.Persona;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class Interfaz extends JFrame implements ActionListener {
    private static Interfaz ourInstance = new Interfaz();
    public static Interfaz getInstance() {
        return ourInstance;
    }

    //Elementos que se van a utilizar
    private JPanel principal = new JPanel();
    private JButton convertir = new JButton("Convertir");
    private JTextArea entrada = new JTextArea(20,18);
    private JTextArea salida = new JTextArea(20,18);
    private JComboBox opcion = new JComboBox();
    private JScrollPane scroll;

    private Interfaz() {

        //Se le da evento al boton
        convertir.addActionListener(this);

        //FRAME
        //Se agregan parametros del Frame
        //Medida de la ventana
        this.setSize(new Dimension(500,500));
        //Seleccion del titulo
        this.setTitle("Convertidor");
        //Evita que se manipule su medida
        this.setResizable(false);
        //Finaliza el proceso al cerrar
        this.setDefaultCloseOperation(3);
        //Inicia la ventana principal centrada
        this.setLocationRelativeTo(null);
        //El frame no contiene layout, ya que este sera contenedor del panel
        this.setLayout(null);
        //////////////////////////////////////////////////////////////////////////////////
        //PANEL
        //Se le agregan caracteristicas al panel principal
        principal.setBounds(10,10,480,450 );
        principal.setBackground(Color.LIGHT_GRAY);
        principal.setVisible(true);
        principal.setLayout(new GridBagLayout());

        //Se crea para marcar en que parte de la cuadricula se colocara cada item
        GridBagConstraints gbc = new GridBagConstraints();
        //////////////////////////////////////////////////////////////////////////////////
        //Text Areas
        //Scroll hace que tenga barra de movimiento a ambas textareas
        scroll = new JScrollPane(entrada);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10,10,10,10);
        principal.add(scroll,gbc);

        scroll = new JScrollPane(salida);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10,10,10,10);
        principal.add(scroll,gbc);

        //////////////////////////////////////////////////////////////////////////////////
        //Combo box
        //Se agregan los ITEMS que contendra el combobox
        opcion.addItem("Clase");
        opcion.addItem("XML");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10,10,0,0);
        principal.add(opcion,gbc);


        //////////////////////////////////////////////////////////////////////////////////
        // Boton
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10,10,0,0);
        principal.add(convertir,gbc);

        //se el panel con los componentes dentro
        this.add(principal);
    }

    public static void main(String[] args){
        //Se instancia toda la parte grafica
        Interfaz obj = new Interfaz();
        obj.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        //Declaracion de variables y toma de texto entrante
        String textoEntrada = entrada.getText();
        entrada.setText("");
        Persona p;
        String[] partes;
        Convertidor convertidor;

        //Se corrobora que tenga al menos 1 caracter en el textbox de entrada
        if(textoEntrada.trim().length()==0){
            JOptionPane.showMessageDialog(
                    this,
                    "Texto de entrada vacio.",
                    "ERROR",
                    JOptionPane.WARNING_MESSAGE);
        }else{
            //Se checa cual metodo sera necesario
            if(opcion.getSelectedItem().toString()=="XML"){

                convertidor = new Convertidor();
                //Se envia el texto en XML y se recibe texto en formado JSON
                String textoConvertido = convertidor.convertirXmlToJson(textoEntrada);
                salida.setText("");
                salida.setText(textoConvertido);
            }else{
                convertidor = new Convertidor();
                partes = textoEntrada.split("-");
                //Comprueba que los datos esten ingresados correctamente
                if (partes.length<=4) {
                    //Mensaje emergente de error en el formato del objeto persona
                    JOptionPane.showMessageDialog(
                            this,
                            "Ingresa los datos de la siguiente manera separados por guion \n Ejemplo: Nombre-Apellido paterno-Apellido materno-Edad-Nacionalidad.",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                }else{
                    //Se agregan valores a p(persona) y se manda el objeto a el metodo convertirClaseToJson
                    p = new Persona(partes[0],partes[1],partes[2],partes[3],partes[4]);
                    String textoConvertido = convertidor.convertirClaseToJson(p);
                    salida.setText("");
                    salida.setText(textoConvertido);
                }

            }
        }

    }
}

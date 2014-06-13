/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfea;

/**
 *
 * @author sotoxx
 */
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPicker;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;


/**
 * Proof of concept of how to handle webcam video stream from Java
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class capturaImg extends JFrame implements Runnable, WebcamListener, WindowListener, UncaughtExceptionHandler, ItemListener {

	private static final long serialVersionUID = 1L;

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private WebcamPicker picker = null;
        private javax.swing.JButton btncapturar;

	@Override
	public void run() {

		setTitle("Captura imagen...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		addWindowListener(this);

		picker = new WebcamPicker();
		picker.addItemListener(this);
                btncapturar= new JButton();
                btncapturar.setText("capturar");
               btncapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

		webcam = picker.getSelectedWebcam();
                //webcam = Webcam.getWebcams().get(1);

		if (webcam == null) {
			System.out.println("No webcams found...");
			System.exit(1);
		}

		webcam.setViewSize(WebcamResolution.VGA.getSize());
                for(int i=0;i<webcam.getViewSizes().length;i++)
                System.out.println("resoluciones..."+webcam.getViewSizes()[i]);
		webcam.addWebcamListener(capturaImg.this);

		panel = new WebcamPanel(webcam, false);
		panel.setFPSDisplayed(true);
                panel.add(btncapturar);
                btncapturar.setBounds(50, 170, 67, 30);

		add(picker, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
                add(btncapturar,BorderLayout.PAGE_END);

		pack();
		setVisible(true);

		Thread t = new Thread() {

			@Override
			public void run() {
				panel.start();
			}
		};
		t.setName("example-starter");
		t.setDaemon(true);
		t.setUncaughtExceptionHandler(this);
		t.start();
	}
        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
            try {
                // TODO add your handling code here:
                BufferedImage image = webcam.getImage();
                ImageIO.write(image, "PNG", new File("test.png"));
            } catch (IOException ex) {
                Logger.getLogger(capturaImg.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new capturaImg());
	}

	@Override
	public void webcamOpen(WebcamEvent we) {
		System.out.println("webcam open");
	}

	@Override
	public void webcamClosed(WebcamEvent we) {
		System.out.println("webcam closed");
	}

	@Override
	public void webcamDisposed(WebcamEvent we) {
		System.out.println("webcam disposed");
	}

	@Override
	public void webcamImageObtained(WebcamEvent we) {
		// do nothing
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		webcam.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("webcam viewer resumed");
		panel.resume();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("webcam viewer paused");
		panel.pause();
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.err.println(String.format("Exception in thread %s", t.getName()));
		e.printStackTrace();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem() != webcam) {
			if (webcam != null) {

				final WebcamPanel tmp = panel;

				remove(panel);

				webcam.removeWebcamListener(this);
                                
                                for(int i=0;i<webcam.getDevice().getResolutions().length;i++)
                                System.out.println(webcam.getDevice().getResolutions()[i].toString());
                                

				webcam = (Webcam) e.getItem();
                                webcam.close();
				webcam.setViewSize(WebcamResolution.VGA.getSize());
                                webcam.open();
				webcam.addWebcamListener(this);

				System.out.println("selected " + webcam.getName());

				panel = new WebcamPanel(webcam, false);

				add(panel, BorderLayout.CENTER);

				Thread t = new Thread() {

					@Override
					public void run() {
                                            //panel.stop();
						tmp.stop();
                                                panel.setVisible(false);
                                                panel.setVisible(true);
						panel.start();
                                                System.out.println("proceso start ");
                                              
					}
				};
				t.setName("example-stoper");
				t.setDaemon(true);
				t.setUncaughtExceptionHandler(this);
				t.start();
			}
		}
	}
}


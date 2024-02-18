import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.MalformedURLException;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.Options;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.CouchDbRepositorySupport;


public class Main{
    JButton createDatabase, createDocument, updateDatabase, DeleteDocument, viewDatabase;

    public Main() {


        JFrame jFrameWindow = new JFrame("CouchDB Java UI");

        FlowLayout flowLayout = new FlowLayout();

        jFrameWindow.setLayout(flowLayout);

        jFrameWindow.setSize(380, 140);

        jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createDatabase = new JButton("Create Database");
        createDocument = new JButton("Create Document");
        updateDatabase = new JButton("Update Database");
        DeleteDocument = new JButton("Delete Database");
        viewDatabase = new JButton("View Database");

        jFrameWindow.add(createDatabase);
        jFrameWindow.add(createDocument);
        jFrameWindow.add(updateDatabase);
        jFrameWindow.add(viewDatabase);
        jFrameWindow.add(DeleteDocument);

        ButtonEventHandler handler = new ButtonEventHandler();

        createDatabase.addActionListener(handler);
        createDocument.addActionListener(handler);
        updateDatabase.addActionListener(handler);
        DeleteDocument.addActionListener(handler);
        viewDatabase.addActionListener(handler);

        jFrameWindow.setLocationRelativeTo(null);

        jFrameWindow.setVisible(true);
    }

    public static void main(String args[]) throws MalformedURLException
    {
        Main guiApp = new Main();
    }

    private class ButtonEventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == createDatabase)
            {
                try
                {
                    CreateDatabase();
                } catch (MalformedURLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }

            else if (e.getSource() == createDocument)
            {
                try
                {
                    createDocument();
                } catch (MalformedURLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }

            else if (e.getSource() == updateDatabase)
            {
                try
                {
                    UpdateDatabase();
                } catch (MalformedURLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }

            else if (e.getSource() == DeleteDocument)
            {
                try
                {
                    DeleteDocument();
                } catch (MalformedURLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }

            else if (e.getSource() == viewDatabase)
            {
                try
                {
                    ViewDatabase();
                } catch (MalformedURLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }

        public static void DeleteDocument() throws MalformedURLException
        {
            HttpClient httpClient = new StdHttpClient.Builder()
                    .url("http://localhost:5984")
                    .username("admin")
                    .password("possum15")
                    .build();

            String database = JOptionPane.showInputDialog(null, "Which database do you want to delete from?");

            String id = JOptionPane.showInputDialog(null, "Which of the documents do you want deleted? Give the document id");
            String rev = JOptionPane.showInputDialog(null, "Which of the documents do you want deleted? Give the revision code");

                CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
                CouchDbConnector db = new StdCouchDbConnector(database, dbInstance);
                db.delete(id, rev);

                JOptionPane.showMessageDialog(null, "Document successfully deleted");
        }

        public static void ViewDatabase() throws MalformedURLException
        {
            HttpClient httpClient = new StdHttpClient.Builder()
                    .url("http://localhost:5984")
                    .username("admin")
                    .password("possum15")
                    .build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
            CouchDbConnector db = dbInstance.createConnector("mmorpggame", true);

            String id = "a6f9244a457118c8d8eb3ec05800032e";
            String rev = "8-42c67755e8cee4059f536411f2f14c60";
            Weapons weapons = db.get(Weapons.class, id, rev);


        }

        public static void CreateDatabase() throws MalformedURLException
        {
            //Connects and logs into couchdb
            HttpClient httpClient = new StdHttpClient.Builder()
                    .url("http://localhost:5984")
                    .username("admin")
                    .password("possum15")
                    .build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);

            String databaseName = JOptionPane.showInputDialog(null, "What would you like the database to be called?");
            CouchDbConnector db = dbInstance.createConnector(databaseName,true);
            db.createDatabaseIfNotExists();
            JOptionPane.showMessageDialog(null, "The database " + databaseName + " has been created");
        }

        public static void createDocument() throws MalformedURLException
        {
            HttpClient httpClient = new StdHttpClient.Builder()
                    .url("http://localhost:5984")
                    .username("admin")
                    .password("possum15")
                    .build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);

            String database = JOptionPane.showInputDialog(null, "What database will you add the document in?");
            String choiceAsString = JOptionPane.showInputDialog(null, "What type of document will this be? 1=Weapons, 2=Enemies");
            int choice = Integer.parseInt(choiceAsString);

            if(choice == 1)
            {
                String weaponName = JOptionPane.showInputDialog(null, "What would you like the weapon to be called?");
                String weaponType = JOptionPane.showInputDialog(null, "What type is this weapon?");
                String weaponElement = JOptionPane.showInputDialog(null, "What element is this weapon?");
                String weaponDamageAsString = JOptionPane.showInputDialog(null, "How much damage does this weapon do?");
                int weaponDamage = Integer.parseInt(weaponDamageAsString);
                String rarity = JOptionPane.showInputDialog(null, "What is the rarity for this weapon");

                Weapons weapons = new Weapons(weaponName, weaponType, weaponElement, weaponDamage, rarity);

                CouchDbConnector db = dbInstance.createConnector(database, true);

                db.create(weapons);
                JOptionPane.showMessageDialog(null, "The weapons document has been created");
            }

            else if(choice == 2)
            {
                String enemyName = JOptionPane.showInputDialog(null, "What is the enemy called?");
                String enemyType = JOptionPane.showInputDialog(null, "What type of enemy is it");
                String enemyElement = JOptionPane.showInputDialog(null, "What element is the enemy?");
                String levelAsString = JOptionPane.showInputDialog(null, "What level is this enemy?");
                int level = Integer.parseInt(levelAsString);
                String enemyDPSAsString = JOptionPane.showInputDialog(null, "How much damage does the enemy do?");
                int enemyDPS = Integer.parseInt(enemyDPSAsString);

                Enemies enemies = new Enemies(enemyName, enemyType, enemyElement, level, enemyDPS);

                CouchDbConnector db = dbInstance.createConnector(database, true);

                db.create(enemies);

                JOptionPane.showMessageDialog(null, "The enemies document has been created");
            }
            else
                JOptionPane.showMessageDialog(null, "Not a correct parameter");

        }

        public static void UpdateDatabase() throws MalformedURLException
        {
            HttpClient httpClient = new StdHttpClient.Builder()
                    .url("http://localhost:5984")
                    .username("admin")
                    .password("possum15")
                    .build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);

            String choiceAsString = JOptionPane.showInputDialog(null, "What type of document do you want to update? 1=Weapons, 2=Enemies");
            int choice = Integer.parseInt(choiceAsString);

            if(choice == 1)
            {
                String weaponName = JOptionPane.showInputDialog(null, "What would you like to update weapon name as?");
                String weaponType = JOptionPane.showInputDialog(null, "What would you like to update weapon type as?");
                String weaponElement = JOptionPane.showInputDialog(null, "What would you like to update weapon element as");
                String weaponDamageAsString = JOptionPane.showInputDialog(null, "How much damage does the updated weapon do?");
                int weaponDamage = Integer.parseInt(weaponDamageAsString);
                String rarity = JOptionPane.showInputDialog(null, "What is the updated rarity for this weapon");

                Weapons weapons = new Weapons(weaponName, weaponType, weaponElement, weaponDamage, rarity);

                CouchDbConnector db = dbInstance.createConnector("database7", true);

                db.update(weapons);
                JOptionPane.showMessageDialog(null, "The weapons document has been updated");
            }

            else if(choice == 2)
            {
                String enemyName = JOptionPane.showInputDialog(null, "What is the enemy called?");
                String enemyType = JOptionPane.showInputDialog(null, "What type of enemy is it");
                String enemyElement = JOptionPane.showInputDialog(null, "What element is the enemy?");
                String levelAsString = JOptionPane.showInputDialog(null, "What level is this enemy?");
                int level = Integer.parseInt(levelAsString);
                String enemyDPSAsString = JOptionPane.showInputDialog(null, "How much damage does the enemy do?");
                int enemyDPS = Integer.parseInt(enemyDPSAsString);

                Enemies enemies = new Enemies(enemyName, enemyType, enemyElement, level, enemyDPS);

                CouchDbConnector db = dbInstance.createConnector("database7", true);

                db.update(enemies);

                JOptionPane.showMessageDialog(null, "The enemies document has been created");
            }
            else
                JOptionPane.showMessageDialog(null, "Not a correct parameter");

        }
    }
}

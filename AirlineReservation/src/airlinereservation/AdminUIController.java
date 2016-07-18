/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinereservation;

import hibernate.HibernateSingleton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Airline;
import model.Airport;
import model.AvailableFlight;
import model.Flight;
import model.YesNo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author iftekher
 */
public class AdminUIController implements Initializable {
    @FXML
    private ComboBox<String> originBox;
    @FXML
    private ComboBox<String> destinationBox;
    @FXML
    private DatePicker departureDatePicker;
    @FXML
    private DatePicker arrivalDatePicker;
    @FXML
    private TextField flightLengthField;
    @FXML
    private TextField totalBusinessSeatsField;
    @FXML
    private TextField bookedBusinessSeatsField;
    @FXML
    private TextField arrivalTimeField;
    @FXML
    private TextField departureTimeField;
    @FXML
    private TextField totalEconomySeatsField;
    @FXML
    private TextField bookedEconomySeatsField;
    @FXML
    private TextField flightNoAvailableFlightsField;
    @FXML
    private TableView<AvailableFlight> availableFlightsTableView;
    @FXML
    private TableColumn<AvailableFlight, String> flightNumberAvailableFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> originAvailableFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> destinationAvailableFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> departureDateAvailableFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> arrivalDateAvailableFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> isAvailableFlightsTableColumn;
    @FXML
    private TextField flightNumberFlightsField;
    @FXML
    private Text flightNumberAvailabilityText;
    @FXML
    private ComboBox<String> airlineCodeBox;
    @FXML
    private ComboBox<YesNo> smokingAllowedBox;
    @FXML
    private ComboBox<YesNo> businessClassAvailableBox;
    @FXML
    private TableView<Flight> flightsTableView;
    @FXML
    private TableColumn<Flight, String> flightNumberFlightsTableColumn;
    @FXML
    private TableColumn<Flight, String> airlineCodeFlightsTableColumn;
    @FXML
    private TableColumn<Flight, String> smokingAllowedFlightsTableColumn;
    @FXML
    private TableColumn<Flight, String> businessClassAvailableFlightsTableColumn;
    
    private List<Airport> airports;
    private List<Airline> airlines;
    private ObservableList<String> airportCodes;
    private ObservableList<String> airlineCodes;
    
    private List<Flight> flights;
    private List<AvailableFlight> availableFlights;
    private ObservableList<Flight> flightsView;
    private ObservableList<AvailableFlight> availableFlightsView;
    
    private static SessionFactory factory;
    private static Session session;
    private Transaction transaction;
    
    private AvailableFlight availableFlight;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        smokingAllowedBox.getItems().addAll(YesNo.values());
        businessClassAvailableBox.getItems().addAll(YesNo.values());
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        
        airports = new ArrayList<>();
        airlines = new ArrayList<>();
        flights = new ArrayList<>();
        availableFlights = new ArrayList<>();
        transaction = session.beginTransaction();
        try {
            airports = session.createCriteria(Airport.class).list();
            airlines = session.createCriteria(Airline.class).list();
            flights = session.createCriteria(Flight.class).list();
            availableFlights = session.createCriteria(AvailableFlight.class).list();
            transaction.commit();
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        airportCodes = FXCollections.observableArrayList();
        for (int i = 0; i < airports.size(); i++){
            airportCodes.add(airports.get(i).getAirportCode());
        }
        originBox.setItems(airportCodes);
        destinationBox.setItems(airportCodes);
        
        airlineCodes = FXCollections.observableArrayList();
        for (int i = 0; i < airlines.size(); i++){
            airlineCodes.add(airlines.get(i).getAirlineCode());
        }
        airlineCodeBox.setItems(airlineCodes);
        
        flightsView = FXCollections.observableArrayList();
        for (int i = 0; i < flights.size(); i++){
            flightsView.add(flights.get(i));
        }
        flightsTableView.setItems(flightsView);
        flightNumberFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        airlineCodeFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAirlineCode()));
        smokingAllowedFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSmokingAllowed()));
        businessClassAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBusinessClassAvailable()));
        
        availableFlightsView = FXCollections.observableArrayList();
        for (int i = 0; i < availableFlights.size(); i++){
            availableFlightsView.add(availableFlights.get(i));
        }
        availableFlightsTableView.setItems(availableFlightsView);
        flightNumberAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        originAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOrigin()));
        destinationAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDestination()));
        departureDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartureDate()));
        arrivalDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArrivalDate()));
        isAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getIsAvailable()));
    } 

    @FXML
    private void handleAddFlightAction(ActionEvent event) {
        String flightNo = flightNumberFlightsField.getText();
        String airlineCode = airlineCodeBox.getSelectionModel().getSelectedItem();
        String smokingAllowed = smokingAllowedBox.getSelectionModel().getSelectedItem() + "";
        String businessClassAvailable = businessClassAvailableBox.getSelectionModel().getSelectedItem() + "";
        
        Flight flight = new Flight(flightNo, airlineCode, smokingAllowed, businessClassAvailable);
        flights.removeAll(flights);
        
        String origin = "Not Selected";
        String destination = "Not Selected";
        String departureDate = "Not Selected";
        String departureTime = "Not Fixed";
        String arrivalDate = "Not Selected";
        String arrivalTime = "Not Fixed";
        double flightLength = 0.0;
        int totalBusinessClassSeat = 0;
        int bookedBusinessClassSeat = 0;
        int totalEconomyClassSeat = 0;
        int bookedEconomyClassSeat = 0;
        String isAvailable = "No";
        
        AvailableFlight availableFlight = new AvailableFlight(flightNo, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, flightLength, totalBusinessClassSeat, bookedBusinessClassSeat, totalEconomyClassSeat, bookedEconomyClassSeat, isAvailable);
        availableFlights.removeAll(availableFlights);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.save(flight);
            session.save(availableFlight);
            
            flights = session.createCriteria(Flight.class).list();
            availableFlights = session.createCriteria(AvailableFlight.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        flightsView.remove(0, flightsView.size());
        for (int i = 0; i < flights.size(); i++){
            flightsView.add(flights.get(i));
        }
        flightsTableView.setItems(flightsView);
        flightNumberFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        airlineCodeFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAirlineCode()));
        smokingAllowedFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSmokingAllowed()));
        businessClassAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBusinessClassAvailable()));
        
        availableFlightsView.remove(0, availableFlightsView.size());
        for (int i = 0; i < availableFlights.size(); i++){
            availableFlightsView.add(availableFlights.get(i));
        }
        availableFlightsTableView.setItems(availableFlightsView);
        flightNumberAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        originAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOrigin()));
        destinationAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDestination()));
        departureDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartureDate()));
        arrivalDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArrivalDate()));
        isAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getIsAvailable()));
        
        flightNumberFlightsField.setText("");
        airlineCodeBox.getSelectionModel().clearSelection();
        smokingAllowedBox.getSelectionModel().clearSelection();
        businessClassAvailableBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleUpdateFlightAction(ActionEvent event) {
        String flightNo = flightNumberFlightsField.getText();
        String airlineCode = airlineCodeBox.getSelectionModel().getSelectedItem();
        String smokingAllowed = smokingAllowedBox.getSelectionModel().getSelectedItem() + "";
        String businessClassAvailable = businessClassAvailableBox.getSelectionModel().getSelectedItem() + "";
        
        Flight flight = new Flight(flightNo, airlineCode, smokingAllowed, businessClassAvailable);
        flights.removeAll(flights);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.update(flight);
            flights = session.createCriteria(Flight.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        flightsView.remove(0, flightsView.size());
        for (int i = 0; i < flights.size(); i++){
            flightsView.add(flights.get(i));
        }
        flightsTableView.setItems(flightsView);
        flightNumberFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        airlineCodeFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAirlineCode()));
        smokingAllowedFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSmokingAllowed()));
        businessClassAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBusinessClassAvailable()));
        
        flightNumberFlightsField.setText("");
        airlineCodeBox.getSelectionModel().clearSelection();
        smokingAllowedBox.getSelectionModel().clearSelection();
        businessClassAvailableBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRemoveFlightAction(ActionEvent event) {
        String flightNo = flightNumberFlightsField.getText();
        String airlineCode = airlineCodeBox.getSelectionModel().getSelectedItem();
        String smokingAllowed = smokingAllowedBox.getSelectionModel().getSelectedItem() + "";
        String businessClassAvailable = businessClassAvailableBox.getSelectionModel().getSelectedItem() + "";
        
        Flight flight = new Flight(flightNo, airlineCode, smokingAllowed, businessClassAvailable);
        AvailableFlight availableFlight = null;
        for (int i = 0; i < availableFlights.size(); i++){
            if (availableFlights.get(i).getFlightNo().equals(flight.getFlightNo())){
                availableFlight = availableFlights.get(i);
            }
        }
        flights.removeAll(flights);
        availableFlights.removeAll(availableFlights);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.delete(flight);
            session.delete(availableFlight);
            
            flights = session.createCriteria(Flight.class).list();
            availableFlights = session.createCriteria(AvailableFlight.class).list();
            
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        flightsView.remove(0, flightsView.size());
        for (int i = 0; i < flights.size(); i++){
            flightsView.add(flights.get(i));
        }
        flightsTableView.setItems(flightsView);
        flightNumberFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        airlineCodeFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAirlineCode()));
        smokingAllowedFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSmokingAllowed()));
        businessClassAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getBusinessClassAvailable()));
        
        availableFlightsView.remove(0, availableFlightsView.size());
        for (int i = 0; i < availableFlights.size(); i++){
            availableFlightsView.add(availableFlights.get(i));
        }
        availableFlightsTableView.setItems(availableFlightsView);
        flightNumberAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        originAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOrigin()));
        destinationAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDestination()));
        departureDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartureDate()));
        arrivalDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArrivalDate()));
        isAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getIsAvailable()));
        
        flightNumberFlightsField.setText("");
        airlineCodeBox.getSelectionModel().clearSelection();
        smokingAllowedBox.getSelectionModel().clearSelection();
        businessClassAvailableBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleNewFlightAction(ActionEvent event) {
        flightNumberFlightsField.setText("");
        airlineCodeBox.getSelectionModel().clearSelection();
        smokingAllowedBox.getSelectionModel().clearSelection();
        businessClassAvailableBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleSelectFlightAvailableAction(MouseEvent event) {
        availableFlight = availableFlightsTableView.getSelectionModel().getSelectedItem();
        
        flightNoAvailableFlightsField.setText(availableFlight.getFlightNo());
        originBox.getSelectionModel().select(availableFlight.getOrigin());
        destinationBox.getSelectionModel().select(availableFlight.getDestination());
        departureDatePicker.getEditor().setText(availableFlight.getDepartureDate());
        departureTimeField.setText(availableFlight.getDepartureTime());
        arrivalDatePicker.getEditor().setText(availableFlight.getArrivalDate());
        arrivalTimeField.setText(availableFlight.getArrivalTime());
        flightLengthField.setText(availableFlight.getFlightLength() + "");
        totalBusinessSeatsField.setText(availableFlight.getTotalBusinessClassSeat() + "");
        bookedBusinessSeatsField.setText(availableFlight.getBookedBusinessClassSeat() + "");
        totalEconomySeatsField.setText(availableFlight.getTotalEconomyClassSeat() + "");
        bookedEconomySeatsField.setText(availableFlight.getBookedEconomyClassSeat() + "");
    }

    @FXML
    private void handleSelectFlightAction(MouseEvent event) {
        Flight flight = flightsTableView.getSelectionModel().getSelectedItem();
        flightNumberFlightsField.setText(flight.getFlightNo());
        airlineCodeBox.getSelectionModel().select(flight.getAirlineCode());
        smokingAllowedBox.getSelectionModel().select(YesNo.valueOf(flight.getSmokingAllowed()));
        businessClassAvailableBox.getSelectionModel().select(YesNo.valueOf(flight.getBusinessClassAvailable()));
    }


    @FXML
    private void handleMakeAvailableFlightAction(ActionEvent event) {
        availableFlight.setIsAvailable("Yes");
        availableFlights.removeAll(availableFlights);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.update(availableFlight);
            availableFlights = session.createCriteria(AvailableFlight.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        availableFlightsView.remove(0, availableFlightsView.size());
        for (int i = 0; i < availableFlights.size(); i++){
            availableFlightsView.add(availableFlights.get(i));
        }
        availableFlightsTableView.setItems(availableFlightsView);
        flightNumberAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        originAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOrigin()));
        destinationAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDestination()));
        departureDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartureDate()));
        arrivalDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArrivalDate()));
        isAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getIsAvailable()));
        
        flightNoAvailableFlightsField.setText("");
        originBox.getSelectionModel().clearSelection();
        destinationBox.getSelectionModel().clearSelection();
        departureDatePicker.getEditor().setText("");
        departureTimeField.setText("");
        arrivalDatePicker.getEditor().setText("");
        arrivalTimeField.setText("");
        flightLengthField.setText("");
        totalBusinessSeatsField.setText("");
        bookedBusinessSeatsField.setText("");
        totalEconomySeatsField.setText("");
        bookedEconomySeatsField.setText("");
    }

    @FXML
    private void handleMakeDisableFlightAction(ActionEvent event) {
        availableFlight.setIsAvailable("No");
        availableFlights.removeAll(availableFlights);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.update(availableFlight);
            availableFlights = session.createCriteria(AvailableFlight.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        availableFlightsView.remove(0, availableFlightsView.size());
        for (int i = 0; i < availableFlights.size(); i++){
            availableFlightsView.add(availableFlights.get(i));
        }
        availableFlightsTableView.setItems(availableFlightsView);
        flightNumberAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        originAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOrigin()));
        destinationAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDestination()));
        departureDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartureDate()));
        arrivalDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArrivalDate()));
        isAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getIsAvailable()));
        
        flightNoAvailableFlightsField.setText("");
        originBox.getSelectionModel().clearSelection();
        destinationBox.getSelectionModel().clearSelection();
        departureDatePicker.getEditor().setText("");
        departureTimeField.setText("");
        arrivalDatePicker.getEditor().setText("");
        arrivalTimeField.setText("");
        flightLengthField.setText("");
        totalBusinessSeatsField.setText("");
        bookedBusinessSeatsField.setText("");
        totalEconomySeatsField.setText("");
        bookedEconomySeatsField.setText("");
    }

    @FXML
    private void handleUpdateAvailableFilghtAction(ActionEvent event) {
        String flightNumber = flightNoAvailableFlightsField.getText();
        String origin = originBox.getSelectionModel().getSelectedItem();
        String destination = destinationBox.getSelectionModel().getSelectedItem();
        String departureDate = departureDatePicker.getEditor().getText();
        String departureTime = departureTimeField.getText();
        String arrivalDate = arrivalDatePicker.getEditor().getText();
        String arrivalTime = arrivalTimeField.getText();
        double flightLength = Double.parseDouble(flightLengthField.getText());
        int totalBusinessClassSeat = Integer.parseInt(totalBusinessSeatsField.getText());
        int bookedBusinessClassSeat = Integer.parseInt(bookedBusinessSeatsField.getText());
        int totalEconomyClassSeat = Integer.parseInt(totalEconomySeatsField.getText());
        int bookedEconomyClassSeat = Integer.parseInt(bookedEconomySeatsField.getText());
        String isAvailable = availableFlight.getIsAvailable();
        
        AvailableFlight availableFlight = new AvailableFlight(flightNumber, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, flightLength, totalBusinessClassSeat, bookedBusinessClassSeat, totalEconomyClassSeat, bookedEconomyClassSeat, isAvailable);
        availableFlights.removeAll(availableFlights);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.update(availableFlight);
            availableFlights = session.createCriteria(AvailableFlight.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        availableFlightsView.remove(0, availableFlightsView.size());
        for (int i = 0; i < availableFlights.size(); i++){
            availableFlightsView.add(availableFlights.get(i));
        }
        availableFlightsTableView.setItems(availableFlightsView);
        flightNumberAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        originAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOrigin()));
        destinationAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDestination()));
        departureDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartureDate()));
        arrivalDateAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArrivalDate()));
        isAvailableFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getIsAvailable()));
        
        flightNoAvailableFlightsField.setText("");
        originBox.getSelectionModel().clearSelection();
        destinationBox.getSelectionModel().clearSelection();
        departureDatePicker.getEditor().setText("");
        departureTimeField.setText("");
        arrivalDatePicker.getEditor().setText("");
        arrivalTimeField.setText("");
        flightLengthField.setText("");
        totalBusinessSeatsField.setText("");
        bookedBusinessSeatsField.setText("");
        totalEconomySeatsField.setText("");
        bookedEconomySeatsField.setText("");
    }


    @FXML
    private void handleRefreshAvailableFilghtAction(ActionEvent event) {
        flightNoAvailableFlightsField.setText("");
        originBox.getSelectionModel().clearSelection();
        destinationBox.getSelectionModel().clearSelection();
        departureDatePicker.getEditor().setText("");
        departureTimeField.setText("");
        arrivalDatePicker.getEditor().setText("");
        arrivalTimeField.setText("");
        flightLengthField.setText("");
        totalBusinessSeatsField.setText("");
        bookedBusinessSeatsField.setText("");
        totalEconomySeatsField.setText("");
        bookedEconomySeatsField.setText("");
    }
    
    @FXML
    private void handleFlightNumberMatchingAction(KeyEvent event) {
        flightNumberAvailabilityText.setText("");
        String flightNumber = flightNumberFlightsField.getText();
        for (int i = 0; i < flights.size(); i++){
            if(flights.get(i).getFlightNo().equals(flightNumber)){
                flightNumberAvailabilityText.setText("Flight Number Already Taken");
                break;
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlinereservation;

import model.Countries;
import hibernate.HibernateSingleton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Airport;
import model.AvailableFlight;
import model.Booking;
import model.BookingClass;
import model.City;
import model.Customer;
import model.Email;
import model.Fax;
import model.Phone;
import model.BookingStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author iftekher
 */
public class BookingOfficeUIController implements Initializable {
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField postCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField provinceField;
    @FXML
    private ComboBox<Countries> countryBox;
    @FXML
    private Text customerIdAvailabilityText;
    @FXML
    private TableView<Customer> customersTableView;
    @FXML
    private TableColumn<Customer, String> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private TableColumn<Customer, String> postCodeColumn;
    @FXML
    private TableColumn<Customer, String> countryColumn;
    @FXML
    private ListView<String> customerIdListView;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField phoneAreaCodeField;
    @FXML
    private TextField phoneCountryCodeField;
    @FXML
    private TextField faxCountryCodeField;
    @FXML
    private TextField faxNumberField;
    @FXML
    private TextField faxAreaCodeField;
    @FXML
    private ListView<Email> emailsListView;
    @FXML
    private ListView<Phone> phonesListView;
    @FXML
    private ListView<Fax> faxesListView;
    @FXML
    private TextField bookingNumberBookingField;
    @FXML
    private ComboBox<String> customerIdBookingBox;
    @FXML
    private ComboBox<String> flightNumberBookingBox;
    @FXML
    private ComboBox<String> bookingStatusBookingBox;
    @FXML
    private ComboBox<String> bookingClassBookingBox;
    @FXML
    private ComboBox<String> originBookingBox;
    @FXML
    private ComboBox<String> destinationBookingBox;
    @FXML
    private ComboBox<String> bookingCityBookingBox;
    @FXML
    private TextField paidByBookingField;
    @FXML
    private DatePicker bookingDatePicker;
    @FXML
    private DatePicker departureBookingDatePicker;
    @FXML
    private DatePicker arrivalBookingDatePicker;
    @FXML
    private TextField departureTimeBookingField;
    @FXML
    private TextField arrivalTimeBookingField;
    @FXML
    private TextField flightPriceBookingField;
    @FXML
    private TextField totalPriceBookingField;
    @FXML
    private TextField paidAmountBookingField;
    @FXML
    private TextField balanceBookingField;
    @FXML
    private TableView<Booking> bookingTableView;
    @FXML
    private TableColumn<Booking, Number> bookingNoBookingTableColumn;
    @FXML
    private TableColumn<Booking, String> customerIdBookingTableColumn;
    @FXML
    private TableColumn<Booking, String> flightNoBookingTableColumn;
    @FXML
    private TableColumn<Booking, String> statusBookingTableColumn;
    @FXML
    private TableColumn<Booking, String> classBookingTableColumn;
    @FXML
    private TableColumn<Booking, Number> totalPriceBookingTableColumn;
    @FXML
    private TableColumn<Booking, Number> paidAmountBookingTableColumn;
    @FXML
    private TableColumn<Booking, String> paidByBookingTableColumn;
    
    @FXML
    private TableView<AvailableFlight> flightsTableView;
    @FXML
    private TableColumn<AvailableFlight, String> flightNoFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> originFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> destinationFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> departureDateFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, String> arrivalDateFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, Number> businessSeatsFlightsTableColumn;
    @FXML
    private TableColumn<AvailableFlight, Number> economySeatsFlightsTableColumn;
    @FXML
    private TableView<Airport> airportsTableView;
    @FXML
    private TableColumn<Airport, String> airportCodeAirportTableColumn;
    @FXML
    private TableColumn<Airport, String> airportNameAirportTableColumn;
    @FXML
    private TableColumn<Airport, String> airportCityAirportTableColumn;
    @FXML
    private TableView<City> citiesTableView;
    @FXML
    private TableColumn<City, String> cityCodeCityTableColumn;
    @FXML
    private TableColumn<City, String> cityNameCityTableColumn;
    @FXML
    private TableColumn<City, String> countryCityTableColumn;
    
    private static SessionFactory factory;
    private static Session session;
    private Transaction transaction;
    
    private List<Customer> customers;
    private List<Email> emails;
    private List<Phone> phones;
    private List<Fax> faxes;
    private List<Booking> bookings;
    private List<AvailableFlight> availableFlights;
    private List<BookingStatus> statuses;
    private List<BookingClass> classes;
    private List<Airport> airports;
    private List<City> cities;
    
    private ObservableList<Customer> customerView;
    private ObservableList<String> customerIds;
    private ObservableList<Email> emailsView;
    private ObservableList<Phone> phonesView;
    private ObservableList<Fax> faxesView;
    private ObservableList<Booking> bookingView;
    private ObservableList<String> flightNos;
    private ObservableList<String> statusIds;
    private ObservableList<String> classIds;
    private ObservableList<String> airportIds;
    private ObservableList<String> cityIds;
    private ObservableList<AvailableFlight> flightsView;
    private ObservableList<Airport> airportsView;
    private ObservableList<City> citiesView;
    
    private Customer customer;
    private Email email;
    private Phone phone;
    private Fax fax;
    private Booking booking;
    private AvailableFlight availableFlight;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        countryBox.getItems().addAll(Countries.values());
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        customers = new ArrayList<>();
        bookings = new ArrayList<>();
        availableFlights = new ArrayList<>();
        statuses = new ArrayList<>();
        classes = new ArrayList<>();
        airports = new ArrayList<>();
        cities = new ArrayList<>();
        try{
            customers = session.createCriteria(Customer.class).list();
            bookings = session.createCriteria(Booking.class).list();
            availableFlights = session.createCriteria(AvailableFlight.class).list();
            statuses = session.createCriteria(BookingStatus.class).list();
            classes = session.createCriteria(BookingClass.class).list();
            airports = session.createCriteria(Airport.class).list();
            cities = session.createCriteria(City.class).list();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        
        customerView = FXCollections.observableArrayList();
        customerIds = FXCollections.observableArrayList();
        for (int i = 0; i < customers.size(); i++){
            customerView.add(customers.get(i));
            customerIds.add(customers.get(i).getCustomerId());
        }
        customersTableView.setItems(customerView);
        customerIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        firstNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName()));
        postCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPostCode()));
        countryColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCountry()));
        
        customerIdListView.setItems(customerIds);
        
        emails = new ArrayList<>();
        phones = new ArrayList<>();
        faxes = new ArrayList<>();
        
        bookingView = FXCollections.observableArrayList();;
        flightNos = FXCollections.observableArrayList();
        statusIds = FXCollections.observableArrayList();
        classIds = FXCollections.observableArrayList();
        airportIds = FXCollections.observableArrayList();
        cityIds = FXCollections.observableArrayList();
        flightsView = FXCollections.observableArrayList();
        airportsView = FXCollections.observableArrayList();
        citiesView = FXCollections.observableArrayList();
        
        for (int i = 0; i < bookings.size(); i++){
            bookingView.add(bookings.get(i));
        }
        bookingTableView.setItems(bookingView);
        bookingNoBookingTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getBookingNo()));
        customerIdBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        flightNoBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        statusBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getStatusId()));
        classBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getClassId()));
        totalPriceBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getTotalPrice()));
        paidAmountBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getPaidAmount()));
        paidByBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPaidBy()));
        
        customerIdBookingBox.setItems(customerIds);
        for (int i = 0; i < availableFlights.size(); i++){
            if(availableFlights.get(i).getIsAvailable().equals("Yes")){
                flightNos.add(availableFlights.get(i).getFlightNo());
                flightsView.add(availableFlights.get(i));
            }
        }
        flightNumberBookingBox.setItems(flightNos);
        flightsTableView.setItems(flightsView);
        flightNoFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        originFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getOrigin()));
        destinationFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDestination()));
        departureDateFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartureDate()));
        arrivalDateFlightsTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getArrivalDate()));
        businessSeatsFlightsTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getTotalBusinessClassSeat() - d.getValue().getBookedBusinessClassSeat()));
        economySeatsFlightsTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getTotalEconomyClassSeat() - d.getValue().getBookedEconomyClassSeat()));
        
        for (int i = 0; i < statuses.size(); i++){
            statusIds.add(statuses.get(i).getStatusId());
        }
        bookingStatusBookingBox.setItems(statusIds);
        
        for (int i = 0; i < classes.size(); i++){
            classIds.add(classes.get(i).getClassId());
        }
        bookingClassBookingBox.setItems(classIds);
        
        for (int i = 0; i < airports.size(); i++){
            airportIds.add(airports.get(i).getAirportCode());
            airportsView.add(airports.get(i));
        }
        originBookingBox.setItems(airportIds);
        destinationBookingBox.setItems(airportIds);
        airportsTableView.setItems(airportsView);
        airportCodeAirportTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAirportCode()));
        airportNameAirportTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAirportName()));
        airportCityAirportTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCityId()));
        
        for (int i = 0; i < cities.size(); i++){
            cityIds.add(cities.get(i).getCityId());
            citiesView.add(cities.get(i));
        }
        bookingCityBookingBox.setItems(cityIds);
        citiesTableView.setItems(citiesView);
        cityCodeCityTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCityId()));
        cityNameCityTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCityName()));
        countryCityTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCountryCode()));
    }    

    @FXML
    private void handleAddCustomerAction(ActionEvent event) {
        String customerId = customerIdField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String province = provinceField.getText();
        String postCode = postCodeField.getText();
        String country = countryBox.getSelectionModel().getSelectedItem() + "";
        
        Customer customer = new Customer(customerId, firstName, lastName, street, city, province, country, postCode);
        customers.removeAll(customers);
        customerView.remove(0, customerView.size());
        customerIds.remove(0, customerIds.size());
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.save(customer);
            customers = session.createCriteria(Customer.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        
        for (int i = 0; i < customers.size(); i++){
            customerView.add(customers.get(i));
            customerIds.add(customers.get(i).getCustomerId());
        }
        customersTableView.setItems(customerView);
        customerIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        firstNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName()));
        postCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPostCode()));
        countryColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCountry()));
        
        customerIdListView.setItems(customerIds);
        
        customerIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        streetField.setText("");
        cityField.setText("");
        provinceField.setText("");
        postCodeField.setText("");
        countryBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleUpdateCustomerAction(ActionEvent event) {
        String customerId = customerIdField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String province = provinceField.getText();
        String postCode = postCodeField.getText();
        String country = countryBox.getSelectionModel().getSelectedItem() + "";
        
        Customer customer = new Customer(customerId, firstName, lastName, street, city, province, country, postCode);
        customers.removeAll(customers);
        customerView.remove(0, customerView.size());
        customerIds.remove(0, customerIds.size());
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.update(customer);
            customers = session.createCriteria(Customer.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        
        for (int i = 0; i < customers.size(); i++){
            customerView.add(customers.get(i));
            customerIds.add(customers.get(i).getCustomerId());
        }
        customersTableView.setItems(customerView);
        customerIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        firstNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName()));
        postCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPostCode()));
        countryColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCountry()));
        
        customerIdListView.setItems(customerIds);
        
        customerIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        streetField.setText("");
        cityField.setText("");
        provinceField.setText("");
        postCodeField.setText("");
        countryBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRemoveCustomerAction(ActionEvent event) {
        String customerId = customerIdField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String province = provinceField.getText();
        String postCode = postCodeField.getText();
        String country = countryBox.getSelectionModel().getSelectedItem() + "";
        
        Customer customer = new Customer(customerId, firstName, lastName, street, city, province, country, postCode);
        customers.removeAll(customers);
        customerView.remove(0, customerView.size());
        customerIds.remove(0, customerIds.size());
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.delete(customer);
            customers = session.createCriteria(Customer.class).list();
            emails = session.createCriteria(Email.class).list();
            phones = session.createCriteria(Phone.class).list();
            faxes = session.createCriteria(Fax.class).list();
            
            for(int i = 0; i < emails.size(); i++){
                if(emails.get(i).getCustomerId().equals(customer.getCustomerId())){
                    session.delete(emails.get(i));
                }
            }
            for(int i = 0; i < phones.size(); i++){
                if(phones.get(i).getCustomerId().equals(customer.getCustomerId())){
                    session.delete(phones.get(i));
                }
            }
            for(int i = 0; i < faxes.size(); i++){
                if(faxes.get(i).getCustomerId().equals(customer.getCustomerId())){
                    session.delete(faxes.get(i));
                }
            }
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        
        for (int i = 0; i < customers.size(); i++){
            customerView.add(customers.get(i));
            customerIds.add(customers.get(i).getCustomerId());
        }
        customersTableView.setItems(customerView);
        customerIdColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        firstNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLastName()));
        postCodeColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPostCode()));
        countryColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCountry()));
        
        customerIdListView.setItems(customerIds);
        
        customerIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        streetField.setText("");
        cityField.setText("");
        provinceField.setText("");
        postCodeField.setText("");
        countryBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRefreshCustomerAction(ActionEvent event) {
        customerIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        streetField.setText("");
        cityField.setText("");
        provinceField.setText("");
        postCodeField.setText("");
        countryBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleAddEmailAction(ActionEvent event) {
        String customerId = customer.getCustomerId();
        String emailId = emailField.getText();
        
        Email email = new Email(customerId, emailId);
     //   customer.addEmail(email);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        emails.removeAll(emails);
        try{
            session.save(email);
            emails = session.createCriteria(Email.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
   //     emails = customer.getEmails();
        emailsView.remove(0, emailsView.size());
        for (int i = 0; i < emails.size(); i++){
            if(emails.get(i).getCustomerId().equals(customerId)){
                emailsView.add(emails.get(i));
            }
        }
        emailsListView.setItems(emailsView);
        
        emailField.setText("");
    }

    @FXML
    private void handleRemoveEmailAction(ActionEvent event) {
        String customerId = customer.getCustomerId();
        String emailId = emailField.getText();
        
        Email email = new Email(customerId, emailId);
        email.setSl(this.email.getSl());
       // customer.addEmail(email);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        emails.removeAll(emails);
        try{
            session.delete(email);
            emails = session.createCriteria(Email.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        emailsView.remove(0, emailsView.size());
        for (int i = 0; i < emails.size(); i++){
            if(emails.get(i).getCustomerId().equals(customerId)){
                emailsView.add(emails.get(i));
            }
        }
        emailsListView.setItems(emailsView);
        
        emailField.setText("");
    }

    @FXML
    private void handleRemovePhoneAction(ActionEvent event) {
        String customerId = customer.getCustomerId();
        String phoneCountryCode = phoneCountryCodeField.getText();
        String phoneAreaCode = phoneAreaCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        
        Phone phone = new Phone(customerId, phoneCountryCode, phoneAreaCode, phoneNumber);
        phone.setSl(this.phone.getSl());
     //   customer.deletePhone(phone);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        phones.removeAll(phones);
        try{
            session.delete(phone);
            phones = session.createCriteria(Phone.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        phonesView.remove(0, phonesView.size());
        for (int i = 0; i < phones.size(); i++){
            if(phones.get(i).getCustomerId().equals(customerId)){
                phonesView.add(phones.get(i));
            }
        }
        phonesListView.setItems(phonesView);
        
        phoneCountryCodeField.setText("");
        phoneAreaCodeField.setText("");
        phoneNumberField.setText("");
    }

    @FXML
    private void handleAddPhoneAction(ActionEvent event) {
        String customerId = customer.getCustomerId();
        String phoneCountryCode = phoneCountryCodeField.getText();
        String phoneAreaCode = phoneAreaCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        
        Phone phone = new Phone(customerId, phoneCountryCode, phoneAreaCode, phoneNumber);
     //   customer.deletePhone(phone);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        phones.removeAll(phones);
        try{
            session.save(phone);
            phones = session.createCriteria(Phone.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        phonesView.remove(0, phonesView.size());
        for (int i = 0; i < phones.size(); i++){
            if(phones.get(i).getCustomerId().equals(customerId)){
                phonesView.add(phones.get(i));
            }
        }
        phonesListView.setItems(phonesView);
        
        phoneCountryCodeField.setText("");
        phoneAreaCodeField.setText("");
        phoneNumberField.setText("");
    }

    @FXML
    private void handleAddFaxAction(ActionEvent event) {
        String customerId = customer.getCustomerId();
        String faxCountryCode = faxCountryCodeField.getText();
        String faxAreaCode = faxAreaCodeField.getText();
        String faxNumber = faxNumberField.getText();
        
        Fax fax = new Fax(customerId, faxCountryCode, faxAreaCode, faxNumber);
      //  customer.addFax(fax);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        faxes.removeAll(faxes);
        try{
            session.save(fax);
            faxes = session.createCriteria(Fax.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
     //   faxes = customer.getFaxes();
        faxesView.remove(0, faxesView.size());
        for (int i = 0; i < faxes.size(); i++){
            if(faxes.get(i).getCustomerId().equals(customerId)){
                faxesView.add(faxes.get(i));
            }
        }
        faxesListView.setItems(faxesView);
        
        faxCountryCodeField.setText("");
        faxAreaCodeField.setText("");
        faxNumberField.setText("");
    }

    @FXML
    private void handleRemoveFaxAction(ActionEvent event) {
        String customerId = customer.getCustomerId();
        String faxCountryCode = faxCountryCodeField.getText();
        String faxAreaCode = faxAreaCodeField.getText();
        String faxNumber = faxNumberField.getText();
        
        Fax fax = new Fax(customerId, faxCountryCode, faxAreaCode, faxNumber);
        fax.setSl(this.fax.getSl());
      //  customer.addFax(fax);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        
        faxes.removeAll(faxes);
        try{
            session.delete(fax);
            faxes = session.createCriteria(Fax.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
     //   faxes = customer.getFaxes();
        faxesView.remove(0, faxesView.size());
        for (int i = 0; i < faxes.size(); i++){
            if(faxes.get(i).getCustomerId().equals(customerId)){
                faxesView.add(faxes.get(i));
            }
        }
        faxesListView.setItems(faxesView);
        
        faxCountryCodeField.setText("");
        faxAreaCodeField.setText("");
        faxNumberField.setText("");
    }

    @FXML
    private void handleCustomerIdMatchingAction(KeyEvent event) {
        customerIdAvailabilityText.setText("");
        String customerId = customerIdField.getText();
        
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getCustomerId().equals(customerId)){
                customerIdAvailabilityText.setText("Customer ID Already Taken");
                break;
            }
        }
    }

    @FXML
    private void handleSelectCustomerAction(MouseEvent event) {
        customer = customersTableView.getSelectionModel().getSelectedItem();
        
        customerIdField.setText(customer.getCustomerId());
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        streetField.setText(customer.getStreet());
        cityField.setText(customer.getCity());
        provinceField.setText(customer.getProvince());
        postCodeField.setText(customer.getPostCode());
        countryBox.getSelectionModel().select(Countries.valueOf(customer.getCountry()));
    }

    @FXML
    private void handleSelectCustomerIdAction(MouseEvent event) {
        String customerId = customerIdListView.getSelectionModel().getSelectedItem();
        
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getCustomerId().equals(customerId)){
                customer = customers.get(i);
                break;
            }
        }
        
        emails.removeAll(emails);
        phones.removeAll(phones);
        faxes.removeAll(faxes);
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            emails = session.createCriteria(Email.class).list();
            phones = session.createCriteria(Phone.class).list();
            faxes = session.createCriteria(Fax.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        emailsView = FXCollections.observableArrayList();
        phonesView = FXCollections.observableArrayList();
        faxesView = FXCollections.observableArrayList();
        
        for (int i = 0; i < emails.size(); i++){
            if(emails.get(i).getCustomerId().equals(customerId)){
                emailsView.add(emails.get(i));
            }
        }
        for (int i = 0; i < phones.size(); i++){
            if(phones.get(i).getCustomerId().equals(customerId)){
                phonesView.add(phones.get(i));
            }
        }
        for (int i = 0; i < faxes.size(); i++){
            if(faxes.get(i).getCustomerId().equals(customerId)){
                faxesView.add(faxes.get(i));
            }
        }
        
        emailsListView.setItems(emailsView);
        phonesListView.setItems(phonesView);
        faxesListView.setItems(faxesView);
    }

    @FXML
    private void handleSelectEmailAction(MouseEvent event) {
        email = emailsListView.getSelectionModel().getSelectedItem();
        emailField.setText(email.getEmailId());
    }

    @FXML
    private void handleSelectPhoneAction(MouseEvent event) {
        phone = phonesListView.getSelectionModel().getSelectedItem();
        phoneCountryCodeField.setText(phone.getPhoneCountryCode());
        phoneAreaCodeField.setText(phone.getPhoneAreaCode());
        phoneNumberField.setText(phone.getPhoneNumber());
    }

    @FXML
    private void handleSelectFaxAction(MouseEvent event) {
        fax = faxesListView.getSelectionModel().getSelectedItem();
        faxCountryCodeField.setText(fax.getFaxCountryCode());
        faxAreaCodeField.setText(fax.getFaxAreaCode());
        faxNumberField.setText(fax.getFaxNumber());
    }

    @FXML
    private void handleRefreshEmailAction(ActionEvent event) {
        emailField.setText("");
    }

    @FXML
    private void handleRefreshPhoneAction(ActionEvent event) {
        phoneCountryCodeField.setText("");
        phoneAreaCodeField.setText("");
        phoneNumberField.setText("");
    }

    @FXML
    private void handleRefreshFaxAction(ActionEvent event) {
        faxCountryCodeField.setText("");
        faxAreaCodeField.setText("");
        faxNumberField.setText("");
    }

    @FXML
    private void handleflightNumberBookingBoxAction(ActionEvent event) {
        String flightNumber = flightNumberBookingBox.getSelectionModel().getSelectedItem();
        for (int i = 0; i < availableFlights.size(); i++){
            if(availableFlights.get(i).getFlightNo().equals(flightNumber)){
                availableFlight = availableFlights.get(i);
            }
        }
        originBookingBox.getSelectionModel().select(availableFlight.getOrigin());
        destinationBookingBox.getSelectionModel().select(availableFlight.getDestination());
        departureBookingDatePicker.getEditor().setText(availableFlight.getDepartureDate());
        departureTimeBookingField.setText(availableFlight.getDepartureTime());
        arrivalBookingDatePicker.getEditor().setText(availableFlight.getArrivalDate());
        arrivalTimeBookingField.setText(availableFlight.getArrivalTime());
    }

    @FXML
    private void handleAddBookingAction(ActionEvent event) {
        String customerId = customerIdBookingBox.getSelectionModel().getSelectedItem();
        String flightNo = flightNumberBookingBox.getSelectionModel().getSelectedItem();
        String bookingStatus = bookingStatusBookingBox.getSelectionModel().getSelectedItem();
        String bookingClass = bookingClassBookingBox.getSelectionModel().getSelectedItem();
        String origin = originBookingBox.getSelectionModel().getSelectedItem();
        String destination = destinationBookingBox.getSelectionModel().getSelectedItem();
        String departureDate = departureBookingDatePicker.getEditor().getText();
        String departureTime = departureTimeBookingField.getText();
        String arrivalDate = arrivalBookingDatePicker.getEditor().getText();
        String arrivalTime = arrivalTimeBookingField.getText();
        String bookingCity = bookingCityBookingBox.getSelectionModel().getSelectedItem();
        String paidBy = paidByBookingField.getText();
        double flightPrice = Double.parseDouble(flightPriceBookingField.getText());
        double totalPrice = Double.parseDouble(totalPriceBookingField.getText());
        double paidAmount = Double.parseDouble(paidAmountBookingField.getText());
        double balance = Double.parseDouble(balanceBookingField.getText());
        String bookingDate = bookingDatePicker.getEditor().getText();
        
        Booking booking = new Booking(customerId, flightNo, bookingStatus, bookingClass, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, bookingCity, paidBy, flightPrice, totalPrice, paidAmount, balance, bookingDate);
        
        bookings.removeAll(bookings);
        
        for (int i = 0; i < availableFlights.size(); i++){
            if(availableFlights.get(i).getFlightNo().equals(flightNo)){
                availableFlight = availableFlights.get(i);
            }
        }
        if(booking.getClassId().equals("B")){
            availableFlight.setBookedBusinessClassSeat(availableFlight.getBookedBusinessClassSeat() + 1);
        }
        else if(booking.getClassId().equals("E")){
            availableFlight.setBookedEconomyClassSeat(availableFlight.getBookedEconomyClassSeat() + 1);
        }
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.save(booking);
            session.update(availableFlight);
            bookings = session.createCriteria(Booking.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        bookingView.remove(0, bookingView.size());
        for (int i = 0; i < bookings.size(); i++){
            bookingView.add(bookings.get(i));
        }
        bookingTableView.setItems(bookingView);
        bookingNoBookingTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getBookingNo()));
        customerIdBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        flightNoBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        statusBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getStatusId()));
        classBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getClassId()));
        totalPriceBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getTotalPrice()));
        paidAmountBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getPaidAmount()));
        paidByBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPaidBy()));
        
        bookingNumberBookingField.setText("");
        customerIdBookingBox.getSelectionModel().clearSelection();
        flightNumberBookingBox.getSelectionModel().clearSelection();
        bookingStatusBookingBox.getSelectionModel().clearSelection();
        bookingClassBookingBox.getSelectionModel().clearSelection();
        originBookingBox.getSelectionModel().clearSelection();
        destinationBookingBox.getSelectionModel().clearSelection();
        departureBookingDatePicker.getEditor().setText("");
        departureTimeBookingField.setText("");
        arrivalBookingDatePicker.getEditor().setText("");
        arrivalTimeBookingField.setText("");
        bookingCityBookingBox.getSelectionModel().clearSelection();
        paidByBookingField.setText("");
        flightPriceBookingField.setText("");
        totalPriceBookingField.setText("");
        paidAmountBookingField.setText("");
        balanceBookingField.setText("");
        bookingDatePicker.getEditor().setText("");
    }

    @FXML
    private void handleUpdateBookingAction(ActionEvent event) {
        String customerId = customerIdBookingBox.getSelectionModel().getSelectedItem();
        String flightNo = flightNumberBookingBox.getSelectionModel().getSelectedItem();
        String bookingStatus = bookingStatusBookingBox.getSelectionModel().getSelectedItem();
        String bookingClass = bookingClassBookingBox.getSelectionModel().getSelectedItem();
        String origin = originBookingBox.getSelectionModel().getSelectedItem();
        String destination = destinationBookingBox.getSelectionModel().getSelectedItem();
        String departureDate = departureBookingDatePicker.getEditor().getText();
        String departureTime = departureTimeBookingField.getText();
        String arrivalDate = arrivalBookingDatePicker.getEditor().getText();
        String arrivalTime = arrivalTimeBookingField.getText();
        String bookingCity = bookingCityBookingBox.getSelectionModel().getSelectedItem();
        String paidBy = paidByBookingField.getText();
        double flightPrice = Double.parseDouble(flightPriceBookingField.getText());
        double totalPrice = Double.parseDouble(totalPriceBookingField.getText());
        double paidAmount = Double.parseDouble(paidAmountBookingField.getText());
        double balance = Double.parseDouble(balanceBookingField.getText());
        String bookingDate = bookingDatePicker.getEditor().getText();
        
        Booking booking = new Booking(customerId, flightNo, bookingStatus, bookingClass, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, bookingCity, paidBy, flightPrice, totalPrice, paidAmount, balance, bookingDate);
        booking.setBookingNo(this.booking.getBookingNo());
        
        bookings.removeAll(bookings);
        
        for (int i = 0; i < availableFlights.size(); i++){
            if(availableFlights.get(i).getFlightNo().equals(flightNo)){
                availableFlight = availableFlights.get(i);
            }
        }
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.update(booking);
            bookings = session.createCriteria(Booking.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        bookingView.remove(0, bookingView.size());
        for (int i = 0; i < bookings.size(); i++){
            bookingView.add(bookings.get(i));
        }
        bookingTableView.setItems(bookingView);
        bookingNoBookingTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getBookingNo()));
        customerIdBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        flightNoBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        statusBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getStatusId()));
        classBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getClassId()));
        totalPriceBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getTotalPrice()));
        paidAmountBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getPaidAmount()));
        paidByBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPaidBy()));
        
        bookingNumberBookingField.setText("");
        customerIdBookingBox.getSelectionModel().clearSelection();
        flightNumberBookingBox.getSelectionModel().clearSelection();
        bookingStatusBookingBox.getSelectionModel().clearSelection();
        bookingClassBookingBox.getSelectionModel().clearSelection();
        originBookingBox.getSelectionModel().clearSelection();
        destinationBookingBox.getSelectionModel().clearSelection();
        departureBookingDatePicker.getEditor().setText("");
        departureTimeBookingField.setText("");
        arrivalBookingDatePicker.getEditor().setText("");
        arrivalTimeBookingField.setText("");
        bookingCityBookingBox.getSelectionModel().clearSelection();
        paidByBookingField.setText("");
        flightPriceBookingField.setText("");
        totalPriceBookingField.setText("");
        paidAmountBookingField.setText("");
        balanceBookingField.setText("");
        bookingDatePicker.getEditor().setText("");
    }

    @FXML
    private void handleRefreshBookingAction(ActionEvent event) {
        bookingNumberBookingField.setText("");
        customerIdBookingBox.getSelectionModel().clearSelection();
        flightNumberBookingBox.getSelectionModel().clearSelection();
        bookingStatusBookingBox.getSelectionModel().clearSelection();
        bookingClassBookingBox.getSelectionModel().clearSelection();
        originBookingBox.getSelectionModel().clearSelection();
        destinationBookingBox.getSelectionModel().clearSelection();
        departureBookingDatePicker.getEditor().setText("");
        departureTimeBookingField.setText("");
        arrivalBookingDatePicker.getEditor().setText("");
        arrivalTimeBookingField.setText("");
        bookingCityBookingBox.getSelectionModel().clearSelection();
        paidByBookingField.setText("");
        flightPriceBookingField.setText("");
        totalPriceBookingField.setText("");
        paidAmountBookingField.setText("");
        balanceBookingField.setText("");
        bookingDatePicker.getEditor().setText("");
    }

    @FXML
    private void handleSelectBookingAction(MouseEvent event) {
        booking = bookingTableView.getSelectionModel().getSelectedItem();
        
        bookingNumberBookingField.setText(booking.getBookingNo() + "");
        customerIdBookingBox.getSelectionModel().select(booking.getCustomerId());
        flightNumberBookingBox.getSelectionModel().select(booking.getFlightNo());
        bookingStatusBookingBox.getSelectionModel().select(booking.getStatusId());
        bookingClassBookingBox.getSelectionModel().select(booking.getClassId());
        originBookingBox.getSelectionModel().select(booking.getOrigin());
        destinationBookingBox.getSelectionModel().select(booking.getDestination());
        departureBookingDatePicker.getEditor().setText(booking.getDepartureDate());
        departureTimeBookingField.setText(booking.getDepartureTime());
        arrivalBookingDatePicker.getEditor().setText(booking.getArrivalDate());
        arrivalTimeBookingField.setText(booking.getArrivalTime());
        bookingCityBookingBox.getSelectionModel().select(booking.getBookingCity());
        paidByBookingField.setText(booking.getPaidBy());
        flightPriceBookingField.setText(booking.getFlightPrice() + "");
        totalPriceBookingField.setText(booking.getTotalPrice() + "");
        paidAmountBookingField.setText(booking.getPaidAmount() + "");
        balanceBookingField.setText(booking.getBalance() + "");
        bookingDatePicker.getEditor().setText(booking.getBookingDate());
    }

    @FXML
    private void handleCalculateBalanceAction(KeyEvent event) {
        double totalAmount = Double.parseDouble(totalPriceBookingField.getText());
        double paidAmount = Double.parseDouble(paidAmountBookingField.getText());
        double balance = totalAmount - paidAmount;
        balanceBookingField.setText(balance + "");
    }

    @FXML
    private void handleRemoveBookingAction(ActionEvent event) {
        String customerId = customerIdBookingBox.getSelectionModel().getSelectedItem();
        String flightNo = flightNumberBookingBox.getSelectionModel().getSelectedItem();
        String bookingStatus = bookingStatusBookingBox.getSelectionModel().getSelectedItem();
        String bookingClass = bookingClassBookingBox.getSelectionModel().getSelectedItem();
        String origin = originBookingBox.getSelectionModel().getSelectedItem();
        String destination = destinationBookingBox.getSelectionModel().getSelectedItem();
        String departureDate = departureBookingDatePicker.getEditor().getText();
        String departureTime = departureTimeBookingField.getText();
        String arrivalDate = arrivalBookingDatePicker.getEditor().getText();
        String arrivalTime = arrivalTimeBookingField.getText();
        String bookingCity = bookingCityBookingBox.getSelectionModel().getSelectedItem();
        String paidBy = paidByBookingField.getText();
        double flightPrice = Double.parseDouble(flightPriceBookingField.getText());
        double totalPrice = Double.parseDouble(totalPriceBookingField.getText());
        double paidAmount = Double.parseDouble(paidAmountBookingField.getText());
        double balance = Double.parseDouble(balanceBookingField.getText());
        String bookingDate = bookingDatePicker.getEditor().getText();
        
        Booking booking = new Booking(customerId, flightNo, bookingStatus, bookingClass, origin, destination, departureDate, departureTime, arrivalDate, arrivalTime, bookingCity, paidBy, flightPrice, totalPrice, paidAmount, balance, bookingDate);
        booking.setBookingNo(this.booking.getBookingNo());
        
        bookings.removeAll(bookings);
        
        for (int i = 0; i < availableFlights.size(); i++){
            if(availableFlights.get(i).getFlightNo().equals(flightNo)){
                availableFlight = availableFlights.get(i);
            }
        }
        if(booking.getClassId().equals("B")){
            availableFlight.setBookedBusinessClassSeat(availableFlight.getBookedBusinessClassSeat() - 1);
        }
        else if(booking.getClassId().equals("E")){
            availableFlight.setBookedEconomyClassSeat(availableFlight.getBookedEconomyClassSeat() - 1);
        }
        
        factory = HibernateSingleton.getSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
        try{
            session.delete(booking);
            session.update(availableFlight);
            bookings = session.createCriteria(Booking.class).list();
            transaction.commit();
        }catch(Exception e){
            System.err.println(e);
            transaction.rollback();
        }
        session.close();
        
        bookingView.remove(0, bookingView.size());
        for (int i = 0; i < bookings.size(); i++){
            bookingView.add(bookings.get(i));
        }
        bookingTableView.setItems(bookingView);
        bookingNoBookingTableColumn.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getBookingNo()));
        customerIdBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCustomerId()));
        flightNoBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getFlightNo()));
        statusBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getStatusId()));
        classBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getClassId()));
        totalPriceBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getTotalPrice()));
        paidAmountBookingTableColumn.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getPaidAmount()));
        paidByBookingTableColumn.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPaidBy()));
        
        bookingNumberBookingField.setText("");
        customerIdBookingBox.getSelectionModel().clearSelection();
        flightNumberBookingBox.getSelectionModel().clearSelection();
        bookingStatusBookingBox.getSelectionModel().clearSelection();
        bookingClassBookingBox.getSelectionModel().clearSelection();
        originBookingBox.getSelectionModel().clearSelection();
        destinationBookingBox.getSelectionModel().clearSelection();
        departureBookingDatePicker.getEditor().setText("");
        departureTimeBookingField.setText("");
        arrivalBookingDatePicker.getEditor().setText("");
        arrivalTimeBookingField.setText("");
        bookingCityBookingBox.getSelectionModel().clearSelection();
        paidByBookingField.setText("");
        flightPriceBookingField.setText("");
        totalPriceBookingField.setText("");
        paidAmountBookingField.setText("");
        balanceBookingField.setText("");
        bookingDatePicker.getEditor().setText("");
    }
}

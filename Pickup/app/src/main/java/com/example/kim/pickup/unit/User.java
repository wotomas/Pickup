package com.example.kim.pickup.unit;

/**
 * Created by kim on 2015-10-03.
 */
public class User {
    private String _username;
    private String _password;
    private String _phoneNumber;
    private Boolean _isMale;
    private double _rate;

    public User(){
        _username = "";
        _password = "";
        _phoneNumber = "";
        _isMale = false;
        _rate = 0.0;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public Boolean get_isMale() {
        return _isMale;
    }

    public void set_isMale(Boolean _isMale) {
        this._isMale = _isMale;
    }

    public double get_rate() {
        return _rate;
    }

    public void set_rate(double _rate) {
        this._rate = _rate;
    }

    @Override
    public String toString() {
        return "Users{" +
                "_username='" + _username + '\'' +
                ", _password='" + _password + '\'' +
                ", _phoneNumber='" + _phoneNumber + '\'' +
                ", _isMale=" + _isMale +
                ", _rate=" + _rate +
                '}';
    }
}

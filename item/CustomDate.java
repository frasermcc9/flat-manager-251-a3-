package item;

public class CustomDate {
    private String _year;
    private String _month;
    private String _day;

    public CustomDate(String date) throws InvalidFormatException {
        checkDate(date);
        String[] a = date.split("-");
        _year = a[0];
        _month = a[1];
        _day = a[2];
    }

    private static void checkDate(String date) throws InvalidFormatException {
        if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidFormatException("Date is not a valid format. Please use YYYY-MM-DD");
        }
    }

    public String stringifyDate() {
        return _year + "-" + _month + "-" + _day;
    }

    public String getYear() {
        return _year;
    }
}
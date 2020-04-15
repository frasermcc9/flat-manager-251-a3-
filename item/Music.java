package item;


public class Music extends Item {
    private String _releaseDate;

    public Music(String artist, String title, String releaseDate, String acquireDate, String owner, String cost,
            String musicFormat) throws InvalidFormatException {
        super(artist, title, acquireDate, owner, cost, musicFormat);
        _releaseDate = releaseDate;
    }

    @Override
    public String getDetails() {
        String template = "'%s' by %s, %s. (%s, %s, %s, %s)";

        return String.format(template, _title, _creator, _releaseDate, _format, _owner, _acquireDate, _cost);

    }

    @Override
    public String formatReport() {
        String template = "%s: '%s' by %s (%s)";
        return String.format(template, _owner, _title, _creator, _format);
    }
}

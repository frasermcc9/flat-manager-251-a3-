package item;

public class Music extends Item {
    private String _releaseDate;

    public Music(String artist, String title, String releaseDate, String acquireDate, String owner, String cost,
            String musicFormat) throws InvalidFormatException {
        super(artist, title, acquireDate, owner, cost, musicFormat);
        _releaseDate = releaseDate;

        validInput(musicFormat);
    }

    @Override
    public String getDetails() {
        String template = "'%s' by %s, %s. (%s, %s, %s, %s)";

        return String.format(template, _title, _creator, _releaseDate, _format, _owner, _acquireDate, _cost);

    }


    public void validInput(String musicFormat) throws InvalidFormatException {
        validInput();
        if (!musicFormat.matches("^CD|LP$")) {
            throw new InvalidFormatException("CD Format is invalid. Please ensure format is 'CD' or 'LP'");
        } else if (!_releaseDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidFormatException("Publish Year format is invalid. Please ensure the format is 4 digits.");
        }
    }
}

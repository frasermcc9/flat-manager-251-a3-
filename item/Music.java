package item;

public class Music extends AbstractItem {
    private String _releaseDate;

    /**
     * Creates a new music item.
     * 
     * @param artist      The artist of this music
     * @param title       The title of the music
     * @param releaseDate The date this music released (ISO8601)
     * @param acquireDate The date this music was acquired (ISO8061)
     * @param owner       The owner of this music
     * @param cost        The cost of this music
     * @param musicFormat The format of this music (either CD or LP)
     * @throws InvalidFormatException
     */
    public Music(String artist, String title, String releaseDate, String acquireDate, String owner, String cost,
            String musicFormat) throws InvalidFormatException {
        super(artist, title, acquireDate, owner, cost, musicFormat);
        _releaseDate = releaseDate;

        validInput(musicFormat);
    }

    /**
     * returns a string containing the formatted details of this item.
     */
    @Override
    public String getDetails() {
        String template = "'%s' by %s, %s. (%s, %s, %s, %s)";

        return String.format(template, getTitle(), getCreator(), _releaseDate, getFormat(), getOwner(),
                getAcquireDate(), getCost());

    }

    /**
     * returns a string containing the formatted details of this item for the
     * report.
     */

    @Override
    public String formatReport() {
        String template = "%s: '%s' by %s (%s)";
        return String.format(template, getOwner(), getTitle(), getCreator(), getFormat());
    }

    /**
     * checks if music specific fields are of valid format. If the checks fail,
     * throws InvalidFormatException.
     * 
     * @param musicFormat the format of the music
     * @throws InvalidFormatException
     */
    private void validInput(String musicFormat) throws InvalidFormatException {
        validInput(); // calls validInput() from super class
        if (!musicFormat.matches("^(CD)|(LP)$")) {
            throw new InvalidFormatException("CD Format is invalid. Please ensure format is 'CD' or 'LP'");
        } else if (!_releaseDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new InvalidFormatException("Publish Year format is invalid. Please ensure the format is 4 digits.");
        }
    }
}

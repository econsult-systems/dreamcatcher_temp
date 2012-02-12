/**
 * 
 */
package org.dreamcatcher.budget;

import java.util.ArrayList;
import org.openide.util.Exceptions;
import writer.ProjectSettingsException;
import writer.TaggedItem;
import writer.XMLManager;

/**
 * @author Gathoka
 *
 */
public class StandardBudget {
	protected String title;
    protected String artist;
    //protected String album;
    
	protected Object checkBox;
    protected String inUse;
    protected String name;
    protected String description;
    protected String units;
    protected String calculation;
    protected String Values;

    public StandardBudget() {
        title = "";
        artist = "";
        checkBox = "";
		inUse = "";
		name = "";
		description = "";
		units = "";
		calculation = "";
		Values = "";
    }
    
    public StandardBudget(String name_, String description_, Integer units_,
    		Double calculation_, Double totalVelue) {
        title = "";
        artist = "";
        checkBox = "";
		inUse = "";
		name = name_;
		description = description_;
		units = units_.toString();
		calculation = calculation_.toString();
		Values = totalVelue.toString();
    }
    
    public StandardBudget(String name_, String category,String Scenes){
       
        title = "";
        artist = "";
        checkBox = "";
	inUse = Scenes.toString();
	name = name_;
	description = category;
	units = "Hrs";
	calculation = "";
	Values = "";
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Object getAlbum() {
        return checkBox;
    }

    public void setAlbum(Object value) {
        this.checkBox = checkBox;
    }
    
    public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getCalculation() {
		return calculation;
	}

	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}

	public String getValues() {
		return Values;
	}

	public void setValues(String values) {
		Values = values;
	}
}


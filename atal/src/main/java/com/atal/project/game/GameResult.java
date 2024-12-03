package com.atal.project.game;

public class GameResult {

    private double errorMap;
    private double sucess;
    private double empty;
    private double trap;

    public GameResult(double errorMap, double sucess, double empty, double trap) {
        this.errorMap = errorMap;
        this.sucess = sucess;
        this.empty = empty;
        this.trap = trap;
    }

    public GameResult() {

    }

    public double getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(double errorMap) {
        this.errorMap = errorMap;
    }

	public double getSucess() {
		return sucess;
	}

	public void setSucess(double sucess) {
		this.sucess = sucess;
	}

	public double getEmpty() {
		return empty;
	}

	public void setEmpty(double empty) {
		this.empty = empty;
	}

	public double getTrap() {
		return trap;
	}

	public void setTrap(double trap) {
		this.trap = trap;
	}
	
}

package edu.udel.cis.vsl.civl.model.common;

import java.io.PrintStream;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import org.json.JSONObject;

public class SystemCIVLSource implements CIVLSource {

	@Override
	public void print(PrintStream out) {
		out.print("CIVL System object");
	}

	@Override
	public String getLocation() {
		return "CIVL System object";
	}

	@Override
	public String getSummary() {
		return "CIVL System object";
	}

	@Override
	public boolean isSystemSource() {
		return true;
	}

	@Override
	public String getFileName() {
		return "CIVL System object";
	}

	@Override
	public String getContent() {
		return "CIVL System object";
	}

	@Override
	public String toJSONString() {
		JSONObject obj = new JSONObject();
		obj.put("location", getLocation());
		return obj.toString();
	}
}

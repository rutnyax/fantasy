package com.kossyuzokwe.fantasy.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants {

	public static final int STANDARD_PAGE_SIZE = 20;
	public static final String REGISTRATION_EMAIL_SUBJECT = "Registration Confirmation";
	public static final String REGISTRATION_EMAIL_PREAMBLE = "Your registration was successful. Click the following link to verify your account:";
	public static final String RESET_EMAIL_SUBJECT = "Reset Your Password";
	public static final String RESET_EMAIL_PREAMBLE = "You have requested to reset your password. Click the following link to change your password:";
	public static final String SENDER_EMAIL = "admin@fantasy.kossyuzokwe.com";
	public static final Set<String> MADRID_PLAYER_NAMES = new HashSet<>(
			Arrays.asList("Iker Casillas", "Keylor Navas", "Pacheco",
					"Sergio Ramos", "Pepe", "Raphael Varane", "Alvaro Arbeloa",
					"Carvajal", "Fabio Coentrao", "Marcelo", "Nacho Fernandez",
					"Sami Khedira", "Toni Kroos", "Lucas Silva",
					"Asier Illaramendi", "Isco", "James Rodríguez",
					"Gareth Bale", "Luka Modric", "Cristiano Ronaldo",
					"Karim Benzema", "Jesé", "Javier Hernandez"));
	public static final Set<String> BARCELONA_PLAYER_NAMES = new HashSet<>(
			Arrays.asList("Marc-André Ter Stegen", "Martin Montoya",
					"Gerard Pique", "Ivan Rakitic", "Sergio Busquets",
					"Xavier Hernandez", "Pedro Rodriguez", "Andrés Iniesta",
					"Luis Suarez", "Lionel Messi", "Neymar Junior",
					"Rafael Alcantara", "Claudio Bravo", "Javier Mascherano",
					"Marc Bartra", "Douglas", "Jordi Alba", "Sergi Roberto",
					"Adriano", "Dani Alves", "Thomas Vermaelen",
					"Jérémy Mathieu", "Jordi Masip"));
	public static final Set<String> CHELSEA_PLAYER_NAMES = new HashSet<>(
			Arrays.asList("Thibaut Courtois", "Petr Cech", "Cesar Azpilicueta",
					"John Terry", "Gary Cahil", "Kurt Zouma",
					"Andreas Christensen", "Branislav Ivanovic", "Filipe Luis",
					"John Obi Mikel", "Cesc Fabregas", "Ramires", "Nathan Aké",
					"Nemanja Matic", "Oscar", "Willian", "Eden Hazard",
					"Juan Cuadrado", "Loic Remy", "Diego Costa",
					"Didier Drogba", "Ruben Loftus-Cheek", "Isaiah Brown",
					"Jamal Blackman"));
	public static final Set<String> ARSENAL_PLAYER_NAMES = new HashSet<>(
			Arrays.asList("Wojciech Szczesny", "David Ospina",
					"Mathieu Debuchy", "Nacho Monreal", "Kieran Gibbs",
					"Calum Chambers", "Hector Bellerin", "Per Mertesacker",
					"Gabriel Paulista", "Laurent Koscielny", "Tomas Rosicky",
					"Santi Cazorla", "Mikel Arteta", "Mathieu Flamini",
					"Jack Wilshere", "Abou Diaby", "Mesut Ozil",
					"Francis Coquelin", "Alex Oxlade-Chamerlain",
					"Gedion Zelalem", "Aaron Ramsey", "Olivier Giroud",
					"Danny Welbeck", "Theo Walcott", "Alexis Sanchez",
					"Serge Gnabry"));
}

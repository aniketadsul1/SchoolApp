package com.android.sudesi.schoolapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;

@TargetApi(3)
public class MyCalendarActivity extends Activity/* implements OnClickListener */ {
	/*private static final String tag = "MyCalendarActivity";

	private TextView currentMonth;
	private Button selectedDayMonthYearButton;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private GridView calendarView;
	private GridCellAdapter adapter;
	private Calendar _calendar;
	@SuppressLint("NewApi")
	private int month, year;
	AttendanceDetailsModel attendanceDetailsModel,attendanceDetailsModel1;
	private List<AttendanceDetailsModel> attendanceDetailsModelList;
	private List<AttendanceDetailsModel> attendanceDetailsModelList1;


	@SuppressWarnings("unused")
	@SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi" })
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "MMMM yyyy";*/

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	/*	setContentView(R.layout.my_calendar_view);

		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: "
				+ year);

		selectedDayMonthYearButton = (Button) this
				.findViewById(R.id.selectedDayMonthYear);
		selectedDayMonthYearButton.setText("Selected: ");

		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);

		currentMonth = (TextView) this.findViewById(R.id.currentMonth);
		currentMonth.setText(DateFormat.format(dateTemplate,
				_calendar.getTime()));

		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);

		calendarView = (GridView) this.findViewById(R.id.calendar);

		// Initialised
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}

	*//**
         *
         * @param month
         * @param year
         *//*
	private void setGridCellAdapterToDate(int month, int year) {
		adapter = new GridCellAdapter(getApplicationContext(),
				R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(DateFormat.format(dateTemplate,
				_calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if (v == prevMonth) {
			if (month <= 1) {
				month = 12;
				year--;
			} else {
				month--;
			}
			Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
		if (v == nextMonth) {
			if (month > 11) {
				month = 1;
				year++;
			} else {
				month++;
			}
			Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: "
					+ month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}

	}

	@Override
	public void onDestroy() {
		Log.d(tag, "Destroying View ...");
		super.onDestroy();
	}

	// Inner Class
	public class GridCellAdapter extends BaseAdapter implements OnClickListener {
		private static final String tag = "GridCellAdapter";
		private final Context _context;

		private final List<String> list;
		private static final int DAY_OFFSET = 1;
		private final String[] weekdays = new String[] { "Sun", "Mon", "Tue",
				"Wed", "Thu", "Fri", "Sat" };
		private final String[] months = { "January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30,
				31, 30, 31 };
		private int daysInMonth;
		private int currentDayOfMonth;
		private int currentWeekDay;
		private Button gridcell;
		private TextView num_events_per_day;
		private final HashMap<String, Integer> eventsPerMonthMap;
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd-MMM-yyyy");

		// Days in Current Month
		public GridCellAdapter(Context context, int textViewResourceId,
				int month, int year) {
			super();
			this._context = context;
			this.list = new ArrayList<String>();
			Log.d(tag, "==> Passed in Date FOR Month: " + month + " "
					+ "Year: " + year);
			Calendar calendar = Calendar.getInstance();
			setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
			setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
			Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
			Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
			Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());

			// Print Month
			printMonth(month, year);

			// Find Number of Events
			eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
		}

		private String getMonthAsString(int i) {
			return months[i];
		}

		private String getWeekDayAsString(int i) {
			return weekdays[i];
		}

		private int getNumberOfDaysOfMonth(int i) {
			return daysOfMonth[i];
		}

		public String getItem(int position) {
			return list.get(position);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		*//**
         * Prints Month
         *
         * @param mm
         * @param yy
         *//*
		private void printMonth(int mm, int yy) {
			Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
			int trailingSpaces = 0;
			int daysInPrevMonth = 0;
			int prevMonth = 0;
			int prevYear = 0;
			int nextMonth = 0;
			int nextYear = 0;

			int currentMonth = mm - 1;
			String currentMonthName = getMonthAsString(currentMonth);
			daysInMonth = getNumberOfDaysOfMonth(currentMonth);

			Log.d(tag, "Current Month: " + " " + currentMonthName + " having "
					+ daysInMonth + " days.");

			GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
			Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

			if (currentMonth == 11) {
				prevMonth = currentMonth - 1;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 0;
				prevYear = yy;
				nextYear = yy + 1;
				Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			} else if (currentMonth == 0) {
				prevMonth = 11;
				prevYear = yy - 1;
				nextYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 1;
				Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			} else {
				prevMonth = currentMonth - 1;
				nextMonth = currentMonth + 1;
				nextYear = yy;
				prevYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:"
						+ prevMonth + " NextMonth: " + nextMonth
						+ " NextYear: " + nextYear);
			}

			int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
			trailingSpaces = currentWeekDay;

			Log.d(tag, "Week Day:" + currentWeekDay + " is "
					+ getWeekDayAsString(currentWeekDay));
			Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
			Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

			if (cal.isLeapYear(cal.get(Calendar.YEAR)))
				if (mm == 2)
					++daysInMonth;
				else if (mm == 3)
					++daysInPrevMonth;

			// Trailing Month days
			for (int i = 0; i < trailingSpaces; i++) {
				Log.d(tag,
						"PREV MONTH:= "
								+ prevMonth
								+ " => "
								+ getMonthAsString(prevMonth)
								+ " "
								+ String.valueOf((daysInPrevMonth
										- trailingSpaces + DAY_OFFSET)
										+ i));
				list.add(String
						.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET)
								+ i)
						+ "-GREY"
						+ "-"
						+ getMonthAsString(prevMonth)
						+ "-"
						+ prevYear);
			}

			// Current Month Days
			for (int i = 1; i <= daysInMonth; i++) {
				Log.d(currentMonthName, String.valueOf(i) + " "
						+ getMonthAsString(currentMonth) + " " + yy);
				if (i == getCurrentDayOfMonth()) {
					list.add(String.valueOf(i) + "-BLUE" + "-"
							+ getMonthAsString(currentMonth) + "-" + yy);
				} else {
					list.add(String.valueOf(i) + "-WHITE" + "-"
							+ getMonthAsString(currentMonth) + "-" + yy);
				}
			}

			// Leading Month days
			for (int i = 0; i < list.size() % 7; i++) {
				Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
				list.add(String.valueOf(i + 1) + "-GREY" + "-"
						+ getMonthAsString(nextMonth) + "-" + nextYear);
			}
		}

		*//**
         * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
         * ALL entries from a SQLite database for that month. Iterate over the
         * List of All entries, and get the dateCreated, which is converted into
         * day.
         *
         * @param year
         * @param month
         * @return
         *//*
		private HashMap<String, Integer> findNumberOfEventsPerMonth(int year,
				int month) {
			HashMap<String, Integer> map = new HashMap<String, Integer>();

			return map;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@RequiresApi(api = Build.VERSION_CODES.O)
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) _context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.screen_gridcell, parent, false);
			}

			// Get a reference to the Day gridcell
			gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
			gridcell.setOnClickListener(this);

			// ACCOUNT FOR SPACING

			Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
			String[] day_color = list.get(position).split("-");
			final String theday = day_color[0];
			final String themonth = day_color[2];
			final String theyear = day_color[3];
			if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null)) {
				if (eventsPerMonthMap.containsKey(theday)) {
					num_events_per_day = (TextView) row
							.findViewById(R.id.num_events_per_day);
					Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
					num_events_per_day.setText(numEvents.toString());
				}
			}








			// Set the Day GridCell
			gridcell.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					Toast.makeText(_context, "text"+theday + "-" + themonth + "-" + theyear, Toast.LENGTH_SHORT).show();
				}
			});
			gridcell.setText(theday);
			gridcell.setTag(theday + "-" + themonth + "-" + theyear);
			Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-"
					+ theyear);

			if (day_color[1].equals("GREY")) {
				gridcell.setTextColor(getResources()
						.getColor(R.color.lightgray02));
			}
			if (day_color[1].equals("WHITE")) {
				gridcell.setTextColor(getResources().getColor(
						R.color.black));
			}
			if (day_color[1].equals("BLUE")) {
				gridcell.setTextColor(getResources().getColor(R.color.orrange));
			}




			attendanceDetailsModelList=new ArrayList<AttendanceDetailsModel>();
			String where = " where attedance = 'P'";
			Cursor cursor = SchoolApp.dbCon.fetchFromSelect(DbHelper.TABLE_DB_ATTENDANCE, where);
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				do {
					attendanceDetailsModel = createrattendancemodel(cursor);
					attendanceDetailsModelList.add(attendanceDetailsModel);
				} while (cursor.moveToNext());
				cursor.close();

			} else {
				Toast.makeText(getApplicationContext(), "No data found..!", Toast.LENGTH_SHORT).show();
			}
			for (int i=0;i<attendanceDetailsModelList.size();i++){
				String date1;
				String attendance;
				final AttendanceDetailsModel attendanceDetailsModel = attendanceDetailsModelList.get(i);

				String dateStr = attendanceDetailsModel.getDate1();
				SimpleDateFormat srcDf = new SimpleDateFormat("MM/dd/yyyy");

				Date date = null;
				try {
					date = srcDf.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				SimpleDateFormat destDf = new SimpleDateFormat("d-MMMM-yyyy");

				date1 = destDf.format(date);

				//date1=convertDate(attendanceDetailsModel.getDate1());


				attendance=attendanceDetailsModel.getAttendance();
				String[] dateParts = date1.split("-");
				String day = dateParts[0];
				String month = dateParts[1];
				String year=dateParts[2];
				if (day.equals(theday)&& themonth.equals(month)&& theyear.equals(year)){
					gridcell.setTextColor(getResources().getColor(R.color.white));
                    gridcell.setBackgroundColor(getResources().getColor(R.color.Green));


                }

			}



			attendanceDetailsModelList1=new ArrayList<AttendanceDetailsModel>();
			String where1 = " where attedance = 'A'";
			Cursor cursor1 = SchoolApp.dbCon.fetchFromSelect(DbHelper.TABLE_DB_ATTENDANCE, where1);
			if (cursor1 != null && cursor1.getCount() > 0) {
				cursor1.moveToFirst();
				do {
					attendanceDetailsModel1 = createrattendancemodel1(cursor1);
					attendanceDetailsModelList1.add(attendanceDetailsModel1);
				} while (cursor1.moveToNext());
				cursor1.close();

			} else {
				Toast.makeText(getApplicationContext(), "No data found..!", Toast.LENGTH_SHORT).show();
			}
			for (int i=0;i<attendanceDetailsModelList1.size();i++){
				String date1;
				String attendance;
				final AttendanceDetailsModel attendanceDetailsModel1 = attendanceDetailsModelList1.get(i);

				String dateStr = attendanceDetailsModel1.getDate1();
				SimpleDateFormat srcDf = new SimpleDateFormat("MM/dd/yyyy");

				Date date = null;
				try {
					date = srcDf.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				SimpleDateFormat destDf = new SimpleDateFormat("d-MMMM-yyyy");

				date1 = destDf.format(date);

				//date1=convertDate(attendanceDetailsModel.getDate1());


				attendance=attendanceDetailsModel1.getAttendance();
				String[] dateParts = date1.split("-");
				String day = dateParts[0];
				String month = dateParts[1];
				String year=dateParts[2];
				if (day.equals(theday)&& themonth.equals(month)&& theyear.equals(year)){
					gridcell.setTextColor(getResources().getColor(R.color.white));
					gridcell.setBackgroundColor(getResources().getColor(R.color.darkorrange));

				}

			}



			return row;
		}

		@Override
		public void onClick(View view) {
			String date_month_year = (String) view.getTag();
			selectedDayMonthYearButton.setText("Selected: " + date_month_year);
			Log.e("Selected date", date_month_year);
			try {
				Date parsedDate = dateFormatter.parse(date_month_year);
				Log.d(tag, "Parsed Date: " + parsedDate.toString());

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		public int getCurrentDayOfMonth() {
			return currentDayOfMonth;
		}

		private void setCurrentDayOfMonth(int currentDayOfMonth) {
			this.currentDayOfMonth = currentDayOfMonth;
		}

		public void setCurrentWeekDay(int currentWeekDay) {
			this.currentWeekDay = currentWeekDay;
		}

		public int getCurrentWeekDay() {
			return currentWeekDay;
		}
	}


	public AttendanceDetailsModel createrattendancemodel(Cursor cursor) {

		attendanceDetailsModel = new AttendanceDetailsModel();
		try {
			attendanceDetailsModel.setDate1(cursor.getString(cursor.getColumnIndex("date")));
			attendanceDetailsModel.setAttendance(cursor.getString(cursor.getColumnIndex("attendance")));


		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceDetailsModel;

	}

	public AttendanceDetailsModel createrattendancemodel1(Cursor cursor) {

		attendanceDetailsModel1 = new AttendanceDetailsModel();
		try {
			attendanceDetailsModel1.setDate1(cursor.getString(cursor.getColumnIndex("date")));
			attendanceDetailsModel1.setAttendance(cursor.getString(cursor.getColumnIndex("attendance")));


		} catch (Exception e) {
			e.printStackTrace();
		}
		return attendanceDetailsModel1;

	}


	@RequiresApi(api = Build.VERSION_CODES.O)
	private static String convertDate(String strDate)
	{
		//for strdate = 2017 July 25

		DateTimeFormatter f = new DateTimeFormatterBuilder().appendPattern("MM/dd/yyyy")
				.toFormatter();

		LocalDate parsedDate = LocalDate.parse(strDate);
		DateTimeFormatter f2 = DateTimeFormatter.ofPattern("d-MMMM-yyyy");

		String newDate = parsedDate.format(f2);

		return newDate;


*/

    }
}

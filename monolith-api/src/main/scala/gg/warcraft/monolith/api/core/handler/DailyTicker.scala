package gg.warcraft.monolith.api.core.handler

class DailyTicker extends Runnable {
  override def run(): Unit = ???
}

/*
    private static final String LAST_DAILY_TICK_KEY = "lastdailytick";

    private final PersistenceService persistenceService;
    private final EventService eventService;

    @Inject
    public DailyTickHandler(PersistenceService persistenceService,
                            EventService eventService) {
        this.persistenceService = persistenceService;
        this.eventService = eventService;
    }

    @Override
    public void run() {
        String lastDailyTick = persistenceService.get(LAST_DAILY_TICK_KEY);
        int lastDailyTickDay = lastDailyTick != null ? Integer.parseInt(lastDailyTick) : -1;

        LocalDateTime currentUtcTime = LocalDateTime.now();
        LocalDateTime currentNewYorkTime = currentUtcTime.minusHours(5);
        int currentNewYorkDay = currentNewYorkTime.getDayOfMonth();

        if (lastDailyTickDay != currentNewYorkDay) {
            Event event = new DailyTickEvent();
            eventService.publish(event);

            persistenceService.set(LAST_DAILY_TICK_KEY, "" + currentNewYorkDay);
        }
    }
 */

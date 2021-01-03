INSERT INTO server_data (data, value)
VALUES ('last_daily_tick', '0')
ON CONFLICT(data) DO NOTHING;

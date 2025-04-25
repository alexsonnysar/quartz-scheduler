INSERT INTO public.record_retention_policy (table_name, retention_days)
VALUES ('receipts', 1);

CREATE TABLE public.receipts (
                                 id SERIAL PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL,
                                 created_date TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO public.receipts (name, created_date)
VALUES
    ('Old Receipt 1', NOW() - INTERVAL '2 days'), -- Older than 1 day
    ('Old Receipt 2', NOW() - INTERVAL '3 days'); -- Older than 1 day

select * from public.receipts;

select * from public.record_retention_policy;

DELETE FROM public.receipts WHERE created_date <= NOW() - INTERVAL '1 day';
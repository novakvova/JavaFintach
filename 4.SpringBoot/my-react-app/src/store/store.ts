import { configureStore } from "@reduxjs/toolkit";
import { apiCategory } from "../services/apiCategory";
import { apiProduct } from "../services/apiProduct.ts";
import { apiAuth } from "../services/apiAuth.ts";

export const store = configureStore({
    reducer: {
        [apiCategory.reducerPath]: apiCategory.reducer,
        [apiProduct.reducerPath]: apiProduct.reducer,
        [apiAuth.reducerPath]: apiAuth.reducer,
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(apiCategory.middleware, apiProduct.middleware, apiAuth.middleware),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
import { configureStore } from "@reduxjs/toolkit";
import { apiCategory } from "../services/apiCategory";
import { apiProduct } from "../services/apiProduct.ts";

export const store = configureStore({
    reducer: {
        [apiCategory.reducerPath]: apiCategory.reducer,
        [apiProduct.reducerPath]: apiProduct.reducer,
    },
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(apiCategory.middleware, apiProduct.middleware),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
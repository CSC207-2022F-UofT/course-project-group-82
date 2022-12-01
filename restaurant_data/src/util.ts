export class AssertionError extends Error {}

export function assert(condition: boolean, message?: string): void | never {
    if (!condition) throw new AssertionError(message);
}

export function sleep(ms: number): Promise<void> {
    return new Promise((resolve) => setTimeout(resolve, ms));
}
